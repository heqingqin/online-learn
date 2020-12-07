package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-11-28
 */
public interface EduVideoService extends IService<EduVideo> {

    //根据课程id删除小节
    void removeByVideoCourseId(String courseId);
}
