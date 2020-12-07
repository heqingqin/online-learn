package com.atguigu.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/30-8:43
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAlyi(MultipartFile file);
    //删除多个视频
    void removeMoreVideo(List<String> videoList);
}
