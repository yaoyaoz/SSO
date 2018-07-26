package yh.app2.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import yh.app2.util.StringUtil;

public class SSOClientFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private String loginPage;
	private String validateTicketUrl;
	private String localExitUrl;
	private String needLoginUrls;// 无需登录拦截的url，使用逗号分隔

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String ticket = request.getParameter("ticket");
		String globalSessionId = request.getParameter("globalSessionId");
		
		System.out.println("app2:SSOClientFilter:globalSessionId=" + globalSessionId);
		
		String url = request.getRequestURL().toString();

		String[] needLoginAry = needLoginUrls.split(",");

		// 除了包含needLoginAry的请求，其他的都不拦截
		if (needLoginAry != null && needLoginAry.length > 0) {
			boolean contains = false;
			for (String str : needLoginAry) {
				if (url.contains(str)) {
					contains = true;
					break;
				}
			}
			if (!contains) {
				logger.info("{}不包含隐私内容，无需SSO拦截",url);
				filterChain.doFilter(request, response);
				return;
			}
		}
		// 登录拦截
		if (StringUtil.isEmpty(username)) {// 本地未登录
			// 中心已经登录
			if (StringUtil.isUnEmpty(ticket)) {
				if (StringUtil.isUnEmpty(globalSessionId)) {
					// 令牌验证
					// 发送请求参数
					PostMethod postMethod = new PostMethod(validateTicketUrl);
					postMethod.addParameter("ticket", ticket);
					postMethod.addParameter("globalSessionId", globalSessionId);
					// localLoginOutUrl
					ServletContext context = request.getServletContext();// 容器
					// String localExitUrl =
					// context.getInitParameter("localExitUrl");
					String basePath = request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + request.getContextPath() + "/";
					postMethod.addParameter("localLoginOutUrl", basePath + localExitUrl);// 退出接口
					postMethod.addParameter("localSessionId", session.getId());// 退出接口
					
					System.out.println("app2:SSOClientFilter:localSessionId=" + session.getId());
					
					// 发送验证请求
					HttpClient httpClient = new HttpClient();
					try {
						httpClient.executeMethod(postMethod);
						String json = postMethod.getResponseBodyAsString();
						postMethod.releaseConnection();
						// 返回
						Map<String, Object> map = new Gson().fromJson(json, new TypeToken<Map<String, Object>>() {
						}.getType());
						int code = ((Double) map.get("code")).intValue();
						if (code == 0) {
							username = (String) map.get("account");
							session.setAttribute("username", username);
							session.setAttribute("globalSessionId", globalSessionId);// 等退出全局登录时使用
							filterChain.doFilter(request, response);
							logger.info("验票成功");
							return;
						} else {
							logger.info("验票失败，重新跳转到sso登录页面");
							response.sendRedirect(loginPage + "?service=" + url);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					logger.info("globalSessionId为空！");
				}

			} else {
				logger.info("ticket为空！跳转到认证中心登录页");
				response.sendRedirect(loginPage + "?service=" + url);
			}
		} else {// 已经登录
			logger.info("已经登录，无需拦截,进入系统:" + request.getContextPath());
			filterChain.doFilter(request, response);
			return;
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		String ssoServer = servletContext.getInitParameter("ssoServerUrl");
		localExitUrl = servletContext.getInitParameter("localExitUrl");
		needLoginUrls = servletContext.getInitParameter("needLoginUrls");

		loginPage = ssoServer + "/auth/toLogin";
		validateTicketUrl = ssoServer + "/ticket/verify";
	}
}