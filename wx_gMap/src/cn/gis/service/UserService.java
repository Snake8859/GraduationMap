package cn.gis.service;

import cn.gis.bean.WxUser;

public interface UserService {

	/**
	 * 判断是否为新用户
	 * @param openId
	 * @return
	 */
	public Long  isNewUser(String openId);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public String save(WxUser user);
	
	/**
	 * 更新用户登录次数
	 * @param openId
	 * @return
	 */
	public int updateLoginNumber(WxUser user);
	
}
