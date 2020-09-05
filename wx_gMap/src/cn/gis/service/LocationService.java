package cn.gis.service;

import cn.gis.bean.WxLocationInfo;

public interface LocationService {

	/**
	 * 保存地址信息
	 * @param wxLocationInfo
	 * @return
	 */
	public String save(WxLocationInfo wxLocationInfo);
}
