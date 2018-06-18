package listener;

import utils.GlobalSessions;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 全局会话的会话监听器
 * Created by yaoyao on 2018-06-18.
 */
public class GlobalSessionListener implements HttpSessionListener {

    //问题：这个类怎么监听的哦？就因为实现了HttpSessionListener么？什么时候会触发下面的方法呢？

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        GlobalSessions.addSession(httpSessionEvent.getSession().getId(), httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        GlobalSessions.delSession(httpSessionEvent.getSession().getId());
    }

}
