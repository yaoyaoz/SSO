package yh.app2.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class LocalSessions {
	private static Map<String, HttpSession> session_cache = new HashMap<>();
	
	public static void put(String sessionId,HttpSession session) {
		session_cache.put(sessionId, session);

	}
	public static HttpSession get(String sessionId) {
		return session_cache.get(sessionId);

	}
	public static void remove(String sessionId) {
		session_cache.remove(sessionId);

	}
}
