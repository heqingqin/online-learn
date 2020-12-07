package com.atguigu.edumsm.service;

import java.util.Map;

/**
 * @author hdoubleq
 * @Date 2020/12/1-15:00
 */
public interface MsmService {
    boolean send(String phone, Map<String, Object> param);
}
