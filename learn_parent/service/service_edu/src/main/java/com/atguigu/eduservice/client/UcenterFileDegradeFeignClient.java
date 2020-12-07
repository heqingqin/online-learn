package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/30-17:12
 */
@Component
public class UcenterFileDegradeFeignClient implements UcenterClient{

    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        return null;
    }
}
