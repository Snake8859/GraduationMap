package cn.gis.dao;

import cn.gis.bean.WxUser;

public interface UserDao {

	/**
	 * 判断微信用户是否已存在
	 * @param openId
	 * @return
	 */
	public Long isNewUser(String openId);
	
	/**
	 * 新增微信用户
	 * @param user
	 * @return
	 */
	public int save(WxUser user);
	
	/**
	 * 更新登录次数
	 * @param openId
	 * @return
	 */
	public int updateLoginNumber(WxUser user);
	
}
