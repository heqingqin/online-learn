package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/30-22:13
 */
@Api(description = "前台页面信息显示")
@RestController
//@CrossOrigin
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;
    @GetMapping("index/{limitT}/{limitC}")
    public R index(@PathVariable long limitT,@PathVariable long limitC){
        //查询前8个优质课程
        List<EduCourse> courses = courseService.list(new QueryWrapper<EduCourse>()
                .orderByDesc("id").last("limit "+limitC));
        //查询前4个名师
        List<EduTeacher> teachers = teacherService.list(new QueryWrapper<EduTeacher>()
                .orderByDesc("id").last("limit "+limitT));
        return R.ok().data("eduList",courses).data("teachList",teachers);
    }

}
