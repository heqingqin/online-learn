package com.atguigu.staservice.service;

import com.atguigu.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author hdoubleq
 * @since 2020-12-03
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    //统计某一天的注册人数，并把他它加到数据库中
    void registerCount(String day);

    Map<String, Object> getChartData(String begin, String end, String type);


}
