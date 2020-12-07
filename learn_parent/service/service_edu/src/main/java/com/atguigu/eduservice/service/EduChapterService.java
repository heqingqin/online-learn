package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-11-28
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id查询章节小结
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
    //根据章节id删除章节
    Boolean deleteChapter(String chapterId);
    //根据课程id删除章节
    void removeByChapterCourseId(String courseId);
}
