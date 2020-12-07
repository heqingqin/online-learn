package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-11-27
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        InputStream in = null;
        try {
            in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1、查询所有的一级分类
        QueryWrapper<EduSubject> wrapperone = new QueryWrapper<>();
        wrapperone.eq("parent_id",0);
        wrapperone.orderByAsc("sort", "id");
        List<EduSubject> oneSubjects = baseMapper.selectList(wrapperone);


        //2、查询所有的二级分类
        QueryWrapper<EduSubject> wrappertwo = new QueryWrapper<>();
        wrappertwo.ne("parent_id",0);
        wrapperone.orderByAsc("sort", "id");
        List<EduSubject> twoSubjects = baseMapper.selectList(wrappertwo);

        //创建list集合，将分类放入集合
        ArrayList<OneSubject> finalSubjectList = new ArrayList<>();
        //3、封装一级分类
        for (EduSubject subject:oneSubjects){
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(subject.getId());
//            oneSubject.setTitle(subject.getTitle());
//            oneSubject.setChildren(new ArrayList<TwoSubject>());
            //简化写法
            BeanUtils.copyProperties(subject,oneSubject);
            finalSubjectList.add(oneSubject);
            //4、封装二级分类
            ArrayList<TwoSubject> twoFinalSubject = new ArrayList<>();
            for (EduSubject tsubject:twoSubjects){
                if (tsubject.getParentId().equals(subject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tsubject,twoSubject);
                    //将二级分类放入一级中
                    twoFinalSubject.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubject);
        }

        return finalSubjectList;
    }
}
