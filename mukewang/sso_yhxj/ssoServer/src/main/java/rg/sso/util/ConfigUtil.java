package rg.sso.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 
 * 类: ProUtil <br>
 * 描述: 属性配置文件读取类 <br>
 * 作者: poseidon<br>
 * 版本: 1.0<br>
 * 时间: 2015-7-17 上午09:20:17
 */
public class ConfigUtil {
	/* 私有构造方法，防止被实例化 */
	private ConfigUtil() {
	};

	private static Properties propertie = null;
	static {
		propertie = new Properties();
		InputStream inputStream = ConfigUtil.class.getResourceAsStream("/config.properties");
		// 解决中文乱码
		BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));
		try {
			propertie.load(buff);
			inputStream.close();
		} catch (IOException e) {
		}
	}

	public static String get(String key) {
		Object o=propertie.get(key);
		if (o==null) {
			return null;
		}
		return o.toString();
	}

	public static void main(String[] args) {
		System.out.println(ConfigUtil.get("name"));
	}
}
