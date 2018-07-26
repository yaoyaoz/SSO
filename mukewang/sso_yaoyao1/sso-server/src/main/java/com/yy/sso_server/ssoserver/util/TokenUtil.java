package com.yy.sso_server.ssoserver.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 令牌缓存
 *
 * Created by yaoyao on 2018/7/26.
 */
public class TokenUtil {

    private static Map<String, String> TICKET_CACHE = new HashMap<>();

    public static void setToken(String ticketId, String account) {
        TICKET_CACHE.put(ticketId, account);
    }

    public static String getToken(String ticketId) {
        return TICKET_CACHE.get(ticketId);
    }

    public static void delToken(String ticketId) {
        TICKET_CACHE.remove(ticketId);
    }

}
