package com.ssoclient.app1.ssoclientapp1.config;

import com.ssoclient.app1.ssoclientapp1.filter.SSOClientFilter;
import com.ssoclient.app1.ssoclientapp1.listener.LocalSessionListener;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
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

    //登出listener
    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> setSingleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>(new SingleSignOutHttpSessionListener());
        return singleSignOutHttpSessionListener;
    }

    //登出filter
    @Bean
    public FilterRegistrationBean setSingleSignOutFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new SingleSignOutFilter());
        filterBean.setName("singleSignOutFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

    //认证filter
    @Bean
    public FilterRegistrationBean setAuthenticationFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new AuthenticationFilter());
        filterBean.setName("authenticationFilter");
        filterBean.addInitParameter("casServerLoginUrl", "http://www.cas.com:8080/login");
        filterBean.addInitParameter("serverName", "http://www.ssoclient.com:8081");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

    //验证票据ticket的filter
    @Bean
    public FilterRegistrationBean setCas20ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        filterBean.setName("cas20ProxyReceivingTicketValidationFilter");
        filterBean.addInitParameter("casServerUrlPrefix", "http://www.cas.com:8080");
        filterBean.addInitParameter("serverName", "http://www.ssoclient.com:8081");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

    //Filter封装标准的HttpRequest
    @Bean
    public FilterRegistrationBean setHttpServletRequestWrapperFilter() {
        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new HttpServletRequestWrapperFilter());
        filterBean.setName("httpServletRequestWrapperFilter");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }

}
