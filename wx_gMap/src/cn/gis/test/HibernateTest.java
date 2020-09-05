package cn.gis.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.gis.bean.WxLocationInfo;
import cn.gis.bean.WxUser;
import cn.gis.utils.DateUtil;

public class HibernateTest {

	@Test
	//hibernate测试 -- 微信用户保存
	public void fun(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//保存微信用户
		WxUser user = new WxUser();
		user.setOpenId("abx888");
		user.setGender("1");
		user.setNickName("Snake8859");
		user.setLoginNumber(1);
		session.save(user);
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	//hibernate测试  -- 地址信息保存
	public void fun1(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//保存微信用户
		WxLocationInfo location = new WxLocationInfo();
		location.setlId("afdsf332");
		location.setOpenId("abx888");
		location.setInitialProvince("湖南省");
		location.setInitialCity("长沙市");
		location.setInitalLat("28.114526");
		location.setInitalLng("112.989897");
		location.setInitialLocation("湖南省长沙市天心区");
		location.setProvince("湖南省");
		location.setCity("长沙市");
		location.setLat("28.146841");
		location.setLng("113.065292");
		location.setLocation("湖南省长沙市雨花区");
		location.setModifyTime(DateUtil.getDay());
		location.setKeyWords("长沙南站");
		session.save(location);
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
