package utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户和SSO服务器的全局会话缓存
 * Created by yaoyao on 2018-06-18.
 */
public class GlobalSessions {

    //存放所有全局会话
    //问题：这个全局会话的key是放的什么哦？
    private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    public static void addSession(String sessionId, HttpSession session) {
        sessions.put(sessionId, session);
    }

    public static void delSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public static HttpSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

}
