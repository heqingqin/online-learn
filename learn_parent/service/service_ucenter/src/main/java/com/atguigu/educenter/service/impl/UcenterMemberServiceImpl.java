package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.awt.windows.ThemeReader;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-01
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(member)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"账号或密码不能为空");
        }
        UcenterMember mobile1 = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));

        if (mobile1==null){
            throw new GuliException(20001,"该账号为注册，请先注册");
        }

        //md5加密
        if (!mobile1.getPassword().equals(MD5.encrypt(password))){
            throw new GuliException(20001,"账号或密码错误");
        }

        if (mobile1.getIsDisabled()){
            throw new GuliException(20001,"该账号已被禁用，请联系管理员解禁");
        }

        //登录成功
        //生成token
        String jwtToken = JwtUtils.getJwtToken(mobile1.getId(), mobile1.getNickname());

        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        //校验参数
        if (StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"请先填写完整信息");
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(mobile);

        if (!mobleCode.equals(code)){
            throw new GuliException(20001,"验证码错误");
        }

        //判断手机号是否已经注册
        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (count>0){
            throw new GuliException(20001,"该手机号已被注册");
        }

        //注册成功
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);//不被禁用
        ucenterMember.setAvatar("http://n.sinaimg.cn/sinacn/w1024h1304/20171201/c5eb-fypikwt2088099.jpg");//默认头像
        baseMapper.insert(ucenterMember);
    }

    //查询数据库当前用用户是否曾经使用过微信登录
    @Override
    public UcenterMember getByOpenid(String openid) {
        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("openid", openid));
        return member;
    }


    //查询某一天注册人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
