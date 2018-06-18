import javax.servlet.http.HttpSessionEvent;

/**
 * 本地会话的监听器
 * Created by yaoyao on 2018-06-18.
 */
public class LocalSessionListenser {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LocalSessions.addSession(httpSessionEvent.getSession().getId(), httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LocalSessions.delSession(httpSessionEvent.getSession().getId());
    }

}
