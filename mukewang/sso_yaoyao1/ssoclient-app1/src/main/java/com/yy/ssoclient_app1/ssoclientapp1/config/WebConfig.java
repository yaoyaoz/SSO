package com.yy.ssoclient_app1.ssoclientapp1.config;

import com.yy.ssoclient_app1.ssoclientapp1.filter.UrlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器、监听器配置
 *
 * Created by yaoyao on 2018/7/26.
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean setUrlFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new UrlFilter());
        filterBean.setName("urlFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

}
