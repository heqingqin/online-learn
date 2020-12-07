package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.mapper.EduCommentMapper;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-02
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Autowired
    private UcenterClient ucenterClient;
    //根据课程id查询评论列表
    @Override
    public Map<String, Object> getPageList(long page, long limit, String courseId) {
        Page<EduComment> page1 = new Page<>(page, limit);
        QueryWrapper<EduComment> course_id = new QueryWrapper<EduComment>().eq("course_id", courseId);
        baseMapper.selectPage(page1, course_id);
        Map<String, Object> map = new HashMap<>();
        List<EduComment> commentList = page1.getRecords();
        map.put("items", commentList);
        map.put("current", page1.getCurrent());
        map.put("pages", page1.getPages());
        map.put("size", page1.getSize());
        map.put("total", page1.getTotal());
        map.put("hasNext", page1.hasNext());
        map.put("hasPrevious", page1.hasPrevious());
        return map;
    }
    //添加评论
    @Override
    public Boolean saveComment(EduComment comment, String memberId) {
        comment.setMemberId(memberId);
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        comment.setNickname(userInfoOrder.getNickname());
        comment.setAvatar(userInfoOrder.getAvatar());
        int insert = baseMapper.insert(comment);
        return insert>0;
    }
}
