package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author hdoubleq
 * @Date 2020/11/26-11:21
 */
@Api(description="登录管理")
@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin  //解决跨域问题
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login(){
        System.out.println("在登录");
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://p0.ssl.img.360kuai.com/t0174dfc65e38f2ad5a.webp");
    }
}
