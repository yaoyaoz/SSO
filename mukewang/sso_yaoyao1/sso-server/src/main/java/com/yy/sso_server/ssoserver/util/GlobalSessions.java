package com.yy.sso_server.ssoserver.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户和SSO服务器的全局会话
 * <p>
 * Created by yaoyao on 2018/7/26.
 */
public class GlobalSessions {

    //存放所有全局会话
    private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    public static void addSession(String sessionId, HttpSession session) {
        sessions.put(sessionId, session);
    }

    public static void delSession(String sessionId) {
        sessions.remove(sessionId);
    }

    //根据id得到session
    public static HttpSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

}
