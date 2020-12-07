package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-03
 */
public interface TPayLogService extends IService<TPayLog> {
    //        返回二维码信息和其他信息
    Map<String, Object> createNative(String orderNo);

    Map<String,String> queryPlayStatus(String orderNo);
    //更改订单状态
    void updateOrderStatus(Map<String,String> map);
}
