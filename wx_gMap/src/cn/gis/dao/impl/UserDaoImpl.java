package cn.gis.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.gis.bean.WxUser;
import cn.gis.dao.UserDao;
import cn.gis.utils.C3P0Utils;
import cn.gis.utils.HibernateUtils;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public Long isNewUser(final String openId) {
		//srping整合hibernate
		//Session session = HibernateUtils.getCurrentSession();
		
		HibernateTemplate ht = this.getHibernateTemplate();
		Long i = ht.execute(new HibernateCallback<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				//HQL
				String hql ="select count(*) from WxUser where openId=?";
				//根据hql语句创建查询对象
				Query query = session.createQuery(hql);
				query.setParameter(0, openId);
				//进行查询
				return (Long) query.uniqueResult();
			}
		});
		return i;
	}

	public int save(final WxUser user) {
		//srping整合hibernate
		//Session session = HibernateUtils.getCurrentSession();
		HibernateTemplate ht = this.getHibernateTemplate();
		Integer i = ht.execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				try{
					session.save(user);
					return 1;
				}
				catch(Exception e){
					return 0;
				}
			}
		});
		return i;
	}

	public int updateLoginNumber(final WxUser user) {
		//srping整合hibernate
		//Session session = HibernateUtils.getCurrentSession();
		HibernateTemplate ht = this.getHibernateTemplate();
		Integer i = ht.execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				try{
					//查询数据库中用户登录次数
					String hql = "select loginNumber from WxUser where openId =?";
					Query query = session.createQuery(hql);
					query.setParameter(0, user.getOpenId());
					int i = (Integer) query.uniqueResult();
					user.setLoginNumber(i+1);
					session.update(user);
					return 1;
				}
				catch(Exception e){
					return 0;
				}
			}
		});
		return i;
		
	}

	//QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
	
	//查询是否为新用户
	/*public Long isNewUser(String openId) throws SQLException {
		String sql ="select count(*) from t_user where openId = ?";
		Long i =(Long) runner.query(sql, new ScalarHandler(), openId);
		return i;
	}*/

	//保存新用户
	/*public int save(WxUser user) throws SQLException {
		//第一次登陆
		user.setLoginNumber(1);
		String sql ="insert into t_user values(?,?,?,?)";
		int i = runner.update(sql, user.getOpenId(),user.getNickName(),user.getGender(),user.getLoginNumber());
		return i;
	}*/

	//登陆次数+1
	/*public int updateLoginNumber(String openId) throws SQLException {
		String sql ="update t_user set loginNumber = loginNumber+1 where openId = ?";
		int i = runner.update(sql, openId);
		return i;
	}*/


}
