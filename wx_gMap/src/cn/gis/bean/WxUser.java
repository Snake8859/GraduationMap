package cn.gis.bean;
/**
 * 
 * @author Snake
 * @version 1.0
 * 	用户实体类
 */
public class WxUser {

	  /*`openId` varchar(64) NOT NULL COMMENT '用户id',
	  `nickName` varchar(64) NOT NULL COMMENT '用户昵称',
	  `sex` char(1) NOT NULL COMMENT '用户性别',
	  `loginNumber` int(11) NOT NULL COMMENT '登录次数',*/
	
	private String openId;  //用户id
	private String nickName; //用户昵称
	private String gender; 		//用户性别
	private int loginNumber; //用户登录次数
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getLoginNumber() {
		return loginNumber;
	}
	public void setLoginNumber(int loginNumber) {
		this.loginNumber = loginNumber;
	}
	
	@Override
	public String toString() {
		return "WxUser [openId=" + openId + ", nickName=" + nickName + ", gender=" + gender + ", loginNumber="
				+ loginNumber + "]";
	}
	
	
	
	
	
	
}
