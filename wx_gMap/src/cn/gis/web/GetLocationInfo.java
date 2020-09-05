package cn.gis.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.gis.bean.WxLocationInfo;
import cn.gis.service.LocationService;
import cn.gis.service.impl.LocationServiceImpl;
import cn.gis.service.impl.UserServiceImpl;
import cn.gis.utils.DateUtil;
import cn.gis.utils.UuidUtil;
/**
 * 
 * @author Snake
 * @version 1.0
 * 用于获得微信前端code去后台换取 openId, sessionKey, unionId的servlet
 *
 */
public class GetLocationInfo extends HttpServlet {
	  
	
	//调用service
	//LocationService service = new LocationService();
	private LocationService service;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决传递到微信中文乱码问题 -- filter过滤器
		
		//获得前端地址数据 -- 直接统一封装
		//获得获得openid
		String openId = request.getParameter("openid");
		//获得初始地
		String initialDistrict = new String(request.getParameter("initalDistrict").getBytes("ISO-8859-1"),"UTF-8");
		//获得目的地
		String district = new String(request.getParameter("district").getBytes("ISO-8859-1"),"UTF-8");
		//获得其余参数
		Map<String, String[]> resultMap = request.getParameterMap();
		//进行乱码处理
		for(Entry<String, String[]> entry :resultMap.entrySet()){
			String value =	new String (entry.getValue()[0].getBytes("ISO-8859-1"),"UTF-8");
			entry.getValue()[0]= value;
			//System.out.println(entry.getKey()+"--- "+entry.getValue()[0]);
		}
		//地址信息封装
		WxLocationInfo wxLocationInfo = new WxLocationInfo();
		wxLocationInfo.setlId(UuidUtil.get32UUID());
		wxLocationInfo.setOpenId(openId);
		wxLocationInfo.setModifyTime(DateUtil.getDay());
		try {
			BeanUtils.populate(wxLocationInfo, resultMap);
			wxLocationInfo.setInitialLocation(wxLocationInfo.getInitialProvince()+wxLocationInfo.getInitialCity()+initialDistrict);
			wxLocationInfo.setLocation(wxLocationInfo.getProvince()+wxLocationInfo.getCity()+district);
			//System.out.println(wxLocationInfo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		//创建容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获得service
		service = (LocationService) ac.getBean("locationService");
		String message = service.save(wxLocationInfo);
		response.getWriter().write(message);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}