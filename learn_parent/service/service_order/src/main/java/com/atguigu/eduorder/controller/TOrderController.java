package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduorder.entity.TOrder;
import com.atguigu.eduorder.entity.TPayLog;
import com.atguigu.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-03
 */
@Api(description = "订单管理")
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    //生成订单
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String jwtToken = JwtUtils.getMemberIdByJwtToken(request);
        //创建订单，返回订单号
        String orderNo = orderService.createOrders(courseId,jwtToken);
        return R.ok().data("orderId",orderNo);
    }

    //根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        TOrder order = orderService.getOne(new QueryWrapper<TOrder>().eq("order_no", orderId));
        return R.ok().data("item",order);
    }

    //查询分页所有订单
    @GetMapping("getOrderInfo/{page}/{limit}")
    public R getOrderAll(@PathVariable long page,@PathVariable long limit){
        Page<TOrder> page1 = new Page<>(page,limit);
        orderService.page(page1,null);
        return R.ok().data("items",page1.getRecords()).data("total",page1.getTotal());
    }


    //根据课程id和用户id查询订单表的订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId){
        int count = orderService.count(new QueryWrapper<TOrder>().eq("course_id", courseId).eq("member_id", memberId).eq("status", 1));
        return count>0;
    }
}

