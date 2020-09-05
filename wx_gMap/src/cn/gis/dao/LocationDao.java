package cn.gis.dao;

import cn.gis.bean.WxLocationInfo;

public interface LocationDao {

	
	/**
	 * 保存地址信息
	 * @param wxLocationInfo
	 * @return
	 */
	public int save(WxLocationInfo wxLocationInfo);
	
}
