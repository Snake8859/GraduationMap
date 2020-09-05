package cn.gis.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.gis.bean.WxLocationInfo;
import cn.gis.dao.LocationDao;
import cn.gis.utils.C3P0Utils;
import cn.gis.utils.HibernateUtils;

public class LocationDaoImpl extends HibernateDaoSupport implements LocationDao {

	public int save(final WxLocationInfo wxLocationInfo) {
		//srping整合hibernate
		//Session session = HibernateUtils.getCurrentSession();
		HibernateTemplate tx = this.getHibernateTemplate();
		Integer i = tx.execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				try{
					session.save(wxLocationInfo);
					return 1;
				}
				catch(Exception e){
					return 0;
				}
			}
		});
		return i;
	}

	
	/*QueryRunner runner  = new QueryRunner(C3P0Utils.getDataSource());
	
	//保存用户基础地址信息
	public int save(WxLocationInfo wxLocationInfo) throws SQLException {
		
		String sql = "insert into t_locationInfo values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int i = runner.update(sql, wxLocationInfo.getlId(),wxLocationInfo.getInitialProvince(),wxLocationInfo.getInitialCity()
				,wxLocationInfo.getInitialLocation(),wxLocationInfo.getInitalLng(),wxLocationInfo.getInitalLat()
				,wxLocationInfo.getProvince(),wxLocationInfo.getCity(),wxLocationInfo.getLocation(),wxLocationInfo.getLng(),
				wxLocationInfo.getLat(),wxLocationInfo.getModifyTime(),wxLocationInfo.getKeyWords(),wxLocationInfo.getOpenId());
		
		return i;
	}*/

}
