package com.atguigu.educenter.mapper;

import com.atguigu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-01
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    //根据时间查询注册人数
    Integer countRegisterDay(String day);
}
