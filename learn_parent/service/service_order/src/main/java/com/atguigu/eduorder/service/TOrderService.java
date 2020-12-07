package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-03
 */
public interface TOrderService extends IService<TOrder> {
    //创建订单，返回订单号
    String createOrders(String courseId, String jwtToken);
}
