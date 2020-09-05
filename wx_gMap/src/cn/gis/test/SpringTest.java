package cn.gis.test;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.gis.bean.WxUser;

public class SpringTest extends HibernateDaoSupport {

	@Test
	//spirng测试
	public void fun(){
		//创建容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获得对象
		WxUser user = (WxUser) ac.getBean("wxUser");
		//打印对象
		if(user!=null){
		System.out.println(user);
		}
	}
	
	@Test
	//spring整合hibernate测试
	public void queryTest(){
		//获得hibernate模板
		HibernateTemplate ht = this.getHibernateTemplate();
		//hql
		WxUser user = ht.execute(new HibernateCallback<WxUser>() {
			public WxUser doInHibernate(Session session) throws HibernateException {
				String hql = "from WxUser where openId=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, "o93v64g0cnHs7qGGrsAVAJkZ1cHo");
				WxUser user = (WxUser) query.uniqueResult();
				//System.out.println(user);
				return user;
			}
		});
		System.out.println(user);
	}
	
	
	
}
