package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author hdoubleq
 * @Date 2020/11/30-17:12
 */
@Component
public class UcenterFileDegradeFeignClient implements UcenterClient{
    @Override
    public R countRegister(String day) {
        return R.error().message("获取数据失败！");
    }
}
