package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-02
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getPageList(long page, long limit, String courseId);
    //添加评论
    Boolean saveComment(EduComment comment, String memberId);
}
