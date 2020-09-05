package cn.gis.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHibernateTest {

	
	@Test
	public void fun(){
		//创建容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获得对象
		SpringTest test = (SpringTest) ac.getBean("springTest");
		test.queryTest();
	}
	
}
