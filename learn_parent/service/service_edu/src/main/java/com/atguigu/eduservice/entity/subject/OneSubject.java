package com.atguigu.eduservice.entity.subject;

import com.microsoft.schemas.office.office.STInsetMode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/28-8:36
 */
//一级分类
@Data
public class OneSubject {
    private String id;
    private String title;

    //一个一级分类中有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
