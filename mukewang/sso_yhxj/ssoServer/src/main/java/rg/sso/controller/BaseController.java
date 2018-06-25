package rg.sso.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title:BaseController
 * @Description:Controller 超类
 * @author 张颖辉
 * @date 2017年9月4日下午9:46:08
 * @version 1.0
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Map<String, Object> map = null;
}
