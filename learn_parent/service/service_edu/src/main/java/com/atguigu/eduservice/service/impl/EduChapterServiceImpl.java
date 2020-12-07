package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-11-28
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    //注入小结的service
    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //查询章节
        QueryWrapper<EduChapter> wrapperChapter=new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        wrapperChapter.orderByAsc("sort");
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
        //查询小结
        QueryWrapper<EduVideo> wrappervideo=new QueryWrapper<>();
        wrappervideo.eq("course_id",courseId);
        wrappervideo.orderByAsc("sort");
        List<EduVideo> eduVideoList = videoService.list(wrappervideo);

        //创建list集合，封装数据
        List<ChapterVo> finalList = new ArrayList<>();
        //遍历查询章节list集合
        for (EduChapter eduChapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);
            //封装小结
            List<VideoVo> videoList = new ArrayList<>();
            for (EduVideo eduVideo : eduVideoList) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);
                }
            }
//            将小结放入
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }
    //删除章节
    @Override
    public Boolean deleteChapter(String chapterId) {
        //判断该章节下是否有小结，如果有则不删否则删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if (count<=0){
            //章节了里面没有小结直接删除
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }else {
            throw new GuliException(20001,"请先删除该章节的小结");
        }
    }



    @Override
    public void removeByChapterCourseId(String courseId) {
        baseMapper.delete(new QueryWrapper<EduChapter>().eq("course_id",courseId));
    }
}
