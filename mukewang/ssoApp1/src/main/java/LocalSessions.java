import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 本地会话缓存，保存所有用户与本服务器的会话id和会话关系
 * Created by yaoyao on 2018-06-18.
 */
public class LocalSessions {

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
