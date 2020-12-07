package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-01
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    //查询数据库当前用用户是否曾经使用过微信登录
    UcenterMember getByOpenid(String openid);
    //根据时间查询注册人数
    Integer countRegisterDay(String day);
}
