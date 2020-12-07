package com.atguigu.eduorder.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hdoubleq
 * @Date 2020/12/2-8:23
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("wx74862e0dfcf69954")
    private String appId;

    @Value("T6m9iK73b0kn9g5v426MKfHQH7X8rKwb")
    private String appSecret;

    @Value("1558950191")
    private String partner;

    @Value("http://guli.shop/api/order/weixinPay/weixinNotify")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;
    public static String WX_OPEN_REDIRECT_NUM;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
        WX_OPEN_REDIRECT_NUM = partner;

    }
}
