package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/30-17:12
 */
@Component
public class OrdersFileDegradeFeignClient implements OrdersClient{
    @Override
    public Boolean isBuyCourse( String courseId, String memberId){
        return false;
    }
}
