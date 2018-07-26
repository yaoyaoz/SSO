package com.yy.sso_server.ssoserver.listener;

import com.yy.sso_server.ssoserver.util.GlobalSessions;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 全局会话的会话监听器
 *
 * Created by yaoyao on 2018/7/26.
 */
public class GlobalSessionListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        httpSession.setMaxInactiveInterval(3*60*60);//过期时间暂定3小时
        GlobalSessions.addSession(httpSession.getId(), httpSession);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        GlobalSessions.delSession(httpSessionEvent.getSession().getId());
    }

}
