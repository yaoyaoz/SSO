package rg.sso.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
/**
 * @Title:GlobalSessions
 * @Description:用户和SSO服务器的全局会话缓存
 * @author 张颖辉
 * @date 2017年9月8日上午10:10:59
 * @version 1.0
 */
public class GlobalSessions {
	private static Map<String, HttpSession> globalSessions_cache = new HashMap<>();
	
	public static void put(String sessionId,HttpSession session) {
		globalSessions_cache.put(sessionId, session);

	}
	public static HttpSession get(String sessionId) {
		return globalSessions_cache.get(sessionId);

	}
	public static void remove(String sessionId) {
		globalSessions_cache.remove(sessionId);

	}
}
