package cn.gis.service.impl;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.gis.bean.WxUser;
import cn.gis.dao.UserDao;
import cn.gis.dao.impl.UserDaoImpl;
import cn.gis.service.UserService;
import cn.gis.utils.HibernateUtils;

public class UserServiceImpl implements UserService {

	//UserDao dao = new UserDaoImpl(); spring管理
	private UserDao dao;
	//判断是否新微信用户
	public Long  isNewUser(String openId){
		Long i = 0L;
		try {
			i = dao.isNewUser(openId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//保存微信用户
	public String save(WxUser user) {
		//从当前线程中获得session 已整合入spring
		//Session session = HibernateUtils.getCurrentSession();
		//开启事物
		//Transaction tx = session.beginTransaction();
		Long count =  isNewUser(user.getOpenId());
		//如果不为新的用户,登录次数+1
		if(count!=0){
			int j =0;
			try {
				j =	updateLoginNumber(user);
				//提交事务  交给spring管理
				//tx.commit();
			} catch (Exception e) {
				//出错了，回滚事务 交给spring管理
				//tx.rollback();
				e.printStackTrace();
			}
			if(j!=0){
				return "老用户登录次数+1";
			}
			else{
				return "老用户登陆次数添加失败";
			}
		}
		//如果为新的用户
		else{
			int i = 0;
			try {
				i = dao.save(user);
				//提交事务 交给spring管理
				//tx.commit();
			} catch (Exception e) {
				//回滚事务 交给spring管理
				//tx.rollback();
				e.printStackTrace();
			}
			if(i!=0){
				return "新用户添加成功";
			}
			else{
				return "新用户添加失败";
			}
		}
	}
	
	public int updateLoginNumber(WxUser user){
		int i=0;
		try{
			i = dao.updateLoginNumber(user);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	
	
}
