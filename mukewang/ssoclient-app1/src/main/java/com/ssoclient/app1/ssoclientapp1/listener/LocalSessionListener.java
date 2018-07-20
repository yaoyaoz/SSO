package com.ssoclient.app1.ssoclientapp1.listener;

import com.ssoclient.app1.ssoclientapp1.util.LocalSessions;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Title:GlobalSessionListener
 * @Description:会话监听器，监听会话的创建和销毁
 * @author 张颖辉
 * @date 2017年9月4日下午9:43:31
 * @version 1.0
 */
//@WebListener
//@Component
public class LocalSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("跳进LocalSessionListener了");
		HttpSession httpSession = event.getSession();
		LocalSessions.put(httpSession.getId(), httpSession);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LocalSessions.remove(event.getSession().getId());
	}

}
