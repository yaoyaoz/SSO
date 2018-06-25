package yh.app2.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import yh.app2.util.LocalSessions;

/**
 * @Title:GlobalSessionListener
 * @Description:Comment for created type
 * @author 张颖辉
 * @date 2017年9月4日下午9:43:31
 * @version 1.0
 */
public class LocalSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession httpSession = event.getSession();
		LocalSessions.put(httpSession.getId(), httpSession);

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LocalSessions.remove(event.getSession().getId());
	}

}
