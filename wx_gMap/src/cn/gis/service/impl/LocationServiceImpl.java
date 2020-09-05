package cn.gis.service.impl;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.gis.bean.WxLocationInfo;
import cn.gis.dao.LocationDao;
import cn.gis.dao.impl.LocationDaoImpl;
import cn.gis.service.LocationService;
import cn.gis.utils.HibernateUtils;

public class LocationServiceImpl implements LocationService {

	
	//LocationDao dao = new LocationDaoImpl(); spring管理
	private LocationDao dao;
	public String save(WxLocationInfo wxLocationInfo) {
		//从当前线程中获得session 已整合入spring
		//Session session = HibernateUtils.getCurrentSession();
		//开启事务
		//Transaction tx = session.beginTransaction();
		int i =0;
		try {
			i =	dao.save(wxLocationInfo);
			//提交事务 交给spring管理
			//tx.commit();
		} catch (Exception e) {
			//回滚事务 交给spring管理
			//tx.rollback();
			e.printStackTrace();
		}
		if(i!=0){
			return "插入一条记录成功";
		}
		else{
			return "插入一条记录失败";
		}
	}
	
	public void setDao(LocationDao dao) {
		this.dao = dao;
	}

	
}
