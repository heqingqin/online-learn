package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.entity.TPayLog;
import com.atguigu.eduorder.service.TPayLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-03
 */
@Api(description = "微信支付")
@RestController
@RequestMapping("/eduorder/paylog")
//@CrossOrigin
public class TPayLogController {
    @Autowired
    private TPayLogService payLogService;

    //生成微信支付端口
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
//        返回二维码信息和其他信息
       Map map = payLogService.createNative(orderNo);
       System.out.println("返回生成支付的二维码集合:"+map);
       return R.ok().data(map);
    }

    //查询支付状态
    @GetMapping("queryPlayStatus/{orderNo}")
    public R queryPlayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPlayStatus(orderNo);
        System.out.println("返回查询支付的二维码集合:"+map);
        if (map==null){
            return R.error().message("支付出错!");
        }else if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }

}

