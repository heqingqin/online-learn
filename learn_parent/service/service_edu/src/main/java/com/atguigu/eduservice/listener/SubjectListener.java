package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

/**
 * @author hdoubleq
 * @Date 2020/11/27-19:29
 */

public class SubjectListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService subjectService;

    public SubjectListener() {
    }

    public SubjectListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(SubjectData data, AnalysisContext analysisContext) {
         if (data==null){
             throw new GuliException(20001,"文件数据为空");
         }
        EduSubject exitoneSubject = this.existOneSubject(subjectService,data.getOneSubjectName());
       //添加一级分类
        if (exitoneSubject==null){
            //没有相同的一级分类就添加
            exitoneSubject = new EduSubject();
            exitoneSubject.setParentId("0");
            exitoneSubject.setTitle(data.getOneSubjectName());
            subjectService.save(exitoneSubject);
        }
        //添加二级分类
        String pid=exitoneSubject.getId();
        EduSubject exitTwoSubject = this.existTwoSubject(subjectService, data.getTwoSubjectName(), pid);

        if (exitTwoSubject==null){
            exitTwoSubject = new EduSubject();
            exitTwoSubject.setParentId(pid);
            exitTwoSubject.setTitle(data.getTwoSubjectName());
            subjectService.save(exitTwoSubject);
        }
    }

    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    //判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }
}
