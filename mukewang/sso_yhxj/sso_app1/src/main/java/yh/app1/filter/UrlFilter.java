package yh.app1.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Title:UrlFilter
 * @Description:跟踪请求的内容和消耗时间
 * @author 张颖辉
 * @date 2017年9月8日上午10:05:58
 * @version 1.0
 */
//登陆状态验证控制过滤器
public class UrlFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String contextPath;

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		
		System.out.println("app1:UrlFilter前：客户端sessionId:" + request.getSession().getId());
		HttpSession session = request.getSession(true);// 若存在会话则返回该会话，否则新建一个会话。
		//问题：看下这里的id和认证中心的globalSessionId是不是一个？
		System.out.println("app1:UrlFilter后 ：客户端sessionId:" + request.getSession().getId());
		
		/**##### basePath路径的保存   #####**/
		String path = request.getContextPath();//	/sso_app1
		
		/**
		 * request.getServerName()是tomcat服务器的端口号
		 * 如果把tomcat的port由8080改为1112，这里basePath就是http://localhost:1112/sso_app1/
		 */
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";//	http://localhost:8080/sso_app1/
		// logger.info(basePath);
		
		request.setAttribute("basePath", basePath);
		//问题：把basePath放到request里面，没看到有request.getAttribute("basePath")的地方也？
		
		/**##### 请求路径打印   #####**/
		String url = request.getServletPath();
		/**
		 * 如果浏览器地址栏输入的是http://localhost:8080/sso_app1，url就是/index.jsp（这个应该是默认路径）
		 * 如果浏览器地址栏输入的是http://localhost:8080/sso_app1/aa.css，url就是/aa.css
		 */
		
		if (url.equals(""))
			url += "/";
		// post请求编码,交给spring过滤器
		request.setCharacterEncoding("utf-8");// 统一编码格式
		
		String loginName = (String) session.getAttribute("loginName");
		/**
		 * 问题：怎么都没看到setAttribute("loginName")的地方，就可以get了呢？
		 * 这里get了loginName，后面都没逻辑用这个。这句话是不是多余的？
		 */
		
		/** 无需验证的 */
		String[] strs = { "/css/", "/js/", "themes", ".css", ".jpg", ".png" }; // 路径中包含这些字符串的,可以不用检查
		// 特殊用途的路径可以直接访问
		if (strs != null && strs.length > 0) {
			for (String str : strs) {
				if (url.indexOf(str) >= 0) {
					filterChain.doFilter(request, response);
					return;
				}
			}
		}
		/**
		 * 
		 * 使用下面的方法打印出所有参数和参数值，会使中文请求出现乱码，解决办法:在上面加入request.setCharacterEncoding(
		 * ) 函数
		 */
		Enumeration<?> enu = request.getParameterNames();
		Map<String, String> parameterMap = new HashMap<String, String>();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			parameterMap.put(paraName, request.getParameter(paraName));
		}
		//问题：如果请求参数里面有中文，这里就会有乱码，咋解决呢？
		
		logger.info("【url日志】 UrlFilter:【" + basePath.substring(0,basePath.lastIndexOf("/"))+url + "】  loginName=" + loginName + " parameterMap="
				+ parameterMap);
		/**********
		 * 避免中文get请求乱码（并且适用于带空格'%20'的getUrl） -zyh （不能和tomcat的设置
		 * URIEncoding="UTF-8" 同时使用）
		 **************/
		// String method=request.getMethod();
		// String json=request.getParameter("json");
		// if
		// ("GET".equals(method.toUpperCase())&&StringHelper.isNotEmpty(json.trim()))
		// {
		// System.out.println("UrlFilter-GET:[原]json="+json);
		// json = json.replaceAll("\n", "&");
		// json=new String(json.getBytes("ISO-8859-1"), "utf-8");
		// request.setAttribute("json4get", json);
		// System.out.println("UrlFilter-GET:[新]json="+json);
		// System.out.println("※ 如果json传递为乱码，请使用
		// request.getAttribute(\"json4get\"); 获取处理后的json。");
		// }
		/** 响应计时 **/
		Long startMillis = System.currentTimeMillis();
		filterChain.doFilter(request, response);
		logger.info("【url日志】UrlFilter【" + basePath.substring(0, basePath.lastIndexOf("/")) + url + "】  :耗时="
				+ (System.currentTimeMillis() - startMillis));
	}

	@Override
	public void destroy() {
		System.out.println(contextPath + " UrlFilter：销毁");

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		contextPath = filterConfig.getServletContext().getContextPath();
		System.out.println(contextPath + " UrlFilter：创建");
	}

}
