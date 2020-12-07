package com.atguigu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hdoubleq
 * @Date 2020/12/1-16:29
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.atguigu"}) //指定扫描位置
@MapperScan("com.atguigu.educenter.mapper")
@EnableDiscoveryClient//nacos注册
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
