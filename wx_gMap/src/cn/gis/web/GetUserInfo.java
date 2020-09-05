package cn.gis.web;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mchange.v2.beans.BeansUtils;
import com.mchange.v2.codegen.bean.BeangenUtils;

import cn.gis.bean.WxUser;
import cn.gis.service.UserService;
import cn.gis.service.impl.UserServiceImpl;

/**
 * 
 * @author Snake
 * @version 1.0
 * 用于获得微信前端的用户基本数据的servlet
 *
 */
public class GetUserInfo extends HttpServlet {
	//调用service
	//UserService service = new UserService();
	private UserService service;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决传递到微信中文乱码问题 -- filter过滤
		String openId = request.getParameter("openId");
		//System.out.println(openId);
		String userInfo = request.getParameter("userInfo");
		//解决微信前端传递到后台的中文乱码
		userInfo = new String(userInfo.getBytes("ISO-8859-1"),"UTF-8");
		//System.out.println(userInfo);
		
		
		// -- 部分逻辑交给service层去做
		
		WxUser user = new WxUser();
		user.setOpenId(openId);
		if(!userInfo.equals("undefined")){
		//用户同意获得头像和名称
		Gson gson = new Gson();
		Map<String, String> retMap = gson.fromJson(userInfo,  
				new TypeToken<Map<String, String>>(){}.getType());
			try {
				BeanUtils.populate(user, retMap);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		else{
		//用户不同意获得头像和名称 -- 由于数据库在设计昵称和性别字段的时候，设置不能为空，所以此处必须赋值给null
		user.setNickName(null);
		user.setGender(null);
		}
		
		//创建容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获得service
		service = (UserService) ac.getBean("userService");
		String message = service.save(user);
		response.getWriter().write(message);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}