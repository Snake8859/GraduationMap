package cn.gis.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.gis.utils.GetOpenIdutil;
/**
 * 
 * @author Snake
 * @version 1.0
 * 用于获得微信前端code去后台换取 openId, sessionKey, unionId的servlet
 *
 */
public class GetOpenId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决传递到微信中文乱码问题 -- filter过滤
		/*
		  -- 写到配置文件里，进行配置
		 	小程序唯一标识符
			AppId 
			小程序密钥
			AppSecret
		*/
		Properties properties = new Properties();
		//使用ClassLoader加载properties配置文件生成对应的输入流
		InputStream in =  GetOpenId.class.getClassLoader().getResourceAsStream("wechat.properties");
		//使用properties对象加载输入流
		properties.load(in);
		//获得key对应的value值
		String appId = properties.getProperty("AppId");
		String appSecret = properties.getProperty("AppSecret");
		//获得微信前台的code
		String code = request.getParameter("code");
		//调用工具类，获得OpenId
		String jsonString = GetOpenIdutil.getopenid(appId, code, appSecret);
		Gson gson = new Gson();
		Map<String, String> retMap = gson.fromJson(jsonString,  
	new TypeToken<Map<String, String>>(){}.getType());
		//System.out.println(retMap.get("openid"));
		response.getWriter().write(retMap.get("openid"));
		
		
		
		/*
		小程序每次request请求都会改变sessionId，如何解决？
		String sessionId = (String) request.getSession().getId();
		System.out.println(sessionId);
		request.getSession().setAttribute("openId", retMap.get("openid"));
		response.getWriter().write(sessionId);
		*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}