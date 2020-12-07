package com.atguigu.staservice.scheduled;


import com.atguigu.staservice.service.StatisticsDailyService;
import com.atguigu.staservice.utils.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

/**
 * @author hdoubleq
 * @Date 2020/12/3-20:47
 */
@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService dailyService;
    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String day = DateUtils.formatDate(DateUtils.addDays(new Date(), -1));
        dailyService.registerCount(day);
    }
}
