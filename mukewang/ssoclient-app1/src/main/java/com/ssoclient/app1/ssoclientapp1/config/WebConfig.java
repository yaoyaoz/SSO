package com.ssoclient.app1.ssoclientapp1.config;

import com.ssoclient.app1.ssoclientapp1.filter.SSOClientFilter;
import com.ssoclient.app1.ssoclientapp1.listener.LocalSessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yaoyao on 2018/7/20.
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean setSSOClientFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new SSOClientFilter());
        filterBean.setName("sSOClientFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

    @Bean
    public ServletListenerRegistrationBean<LocalSessionListener> setLocalSessionListener() {
        ServletListenerRegistrationBean<LocalSessionListener> localSessionListener = new ServletListenerRegistrationBean<LocalSessionListener>(new LocalSessionListener());
        return localSessionListener;
    }

}
