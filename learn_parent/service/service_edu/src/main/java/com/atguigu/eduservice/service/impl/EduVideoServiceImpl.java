package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-11-28
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    VodClient vodClient;
    //删除小节时还要删除视频
    @Override
    public void removeByVideoCourseId(String courseId) {
        List<EduVideo> videoList = baseMapper.selectList(new QueryWrapper<EduVideo>().eq("course_id", courseId).select("video_source_id"));
        List<String> list = new ArrayList<>();
        for (EduVideo eduVideo : videoList) {
            if (!StringUtils.isEmpty(eduVideo.getVideoSourceId()))
            list.add(eduVideo.getVideoSourceId());
        }
        if (list.size()>0)
        vodClient.deleteBatch(list);
        baseMapper.delete(new QueryWrapper<EduVideo>().eq("course_id",courseId));
    }
}
