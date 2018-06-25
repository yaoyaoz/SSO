package rg.sso.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rg.sso.util.Constant;
import rg.sso.util.GlobalSessions;
import rg.sso.util.StringUtil;
import rg.sso.util.TicketUtil;
/**
 * @Title:TicketController
 * @Description:令牌控制器
 * @author 张颖辉
 * @date 2017年9月8日上午10:08:23
 * @version 1.0
 */
@Controller
@RequestMapping("ticket")
public class TicketController extends BaseController {
	/**
	 * @Description:验证令牌
	 * @author 张颖辉
	 * @date 2017年9月5日上午10:20:24
	 * @param ticket
	 * @param localSessionId
	 * @param globalSessionId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("verify")
	public Map<String, Object> verify(String ticket, String localSessionId, String localLoginOutUrl,
			String globalSessionId, HttpServletRequest request) {
		map = new HashMap<>();
		String account = TicketUtil.get(ticket);
		TicketUtil.remove(ticket);
		if (StringUtil.isUnEmpty(account)) {
			logger.info("令牌认证成功");
			// TODO 保存本地会话id和退出接口到 全局会话
			HttpSession httpSession = GlobalSessions.get(globalSessionId);
			Map<String, String> loginOutMap = null;
			if (httpSession.getAttribute("loginOutMap") != null) {
				loginOutMap = (Map<String, String>) httpSession.getAttribute("loginOutMap");// 用户已经登录的应用服务器，map<应用退出接口，应用服务器会话id>
			} else {
				loginOutMap = new HashMap<>();
				httpSession.setAttribute("loginOutMap", loginOutMap);
			}
			loginOutMap.put(localSessionId, localLoginOutUrl);
			// 返回数据
			map.put("code", Constant.CODE_SUCCESS);
			map.put("msg", "令牌认证成功!");
			//map.put("globalSessionId", globalSessionId);// 应用发送给SSO退出请求时使用(应该无需返回)，之前登录生成令牌回调已经发送了全局会话id
			map.put("account", account);
		} else {
			logger.info("令牌认证失败");
			map.put("code", Constant.CODE_FAIL);
			map.put("msg", "令牌认证失败");
		}
		return map;
	}
}
