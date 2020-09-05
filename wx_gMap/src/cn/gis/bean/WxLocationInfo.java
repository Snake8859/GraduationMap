package cn.gis.bean;
/**
 * 
 * @author Snake
 * @version 1.0
 * 	位置信息实体类
 */
public class WxLocationInfo {

	  /*  `lId` varchar(50) NOT NULL COMMENT '位置信息id',
		  `initialProvince` varchar(64) NOT NULL COMMENT '用户初始所在省份',
		  `initialCity` varchar(64) NOT NULL COMMENT '用户初始所在城市',
		  `initialLocation` varchar(255) NOT NULL COMMENT '用户初始所在地',
		  `initialLongitude` double(11,7) NOT NULL COMMENT '用户所在地经度',
		  `initialLatitude` double(11,7) NOT NULL COMMENT '用户所在地纬度',
		  `province` varchar(64) NOT NULL COMMENT '用户将要出发的省份',
		  `city` varchar(64) NOT NULL COMMENT '用户将要出发的城市',
		  `location` varchar(255) NOT NULL COMMENT '用户将要出发的地点',
		  `longitude` double(11,7) NOT NULL COMMENT '用户所在地经度',
		  `latitude` double(11,7) NOT NULL COMMENT '用户所在地纬度',
		  `modifyTime` varchar(64) NOT NULL COMMENT '修改时间',
		  `keyWord` varchar(255) NOT NULL COMMENT '用户输入的出发地',
		  `openId` varchar(64) NOT NULL COMMENT '用户id',*/
		
		private String lId;		//位置信息id
		private String initialProvince; //用户初始所在省份
		private String initialCity;  //用户初始所在城市
		private String initialLocation;  //用户初始所在地
		private String initalLng; //用户所在地经度
		private String initalLat;	  //用户所在地纬度
		private String province; 	//用户将要出发的省份
		private String city;		//用户将要出发的城市
		private String location;	//用户将要出发的地点
		private String lng;   //用户所在地经度
		private String lat;   //用户所在地纬度
		private String modifyTime; //用户修改时间
		private String keyWords;    //用户输入的出发地
		private String openId;		//用户id
		
		public String getlId() {
			return lId;
		}
		public void setlId(String lId) {
			this.lId = lId;
		}
		public String getInitialProvince() {
			return initialProvince;
		}
		public void setInitialProvince(String initialProvince) {
			this.initialProvince = initialProvince;
		}
		public String getInitialCity() {
			return initialCity;
		}
		public void setInitialCity(String initialCity) {
			this.initialCity = initialCity;
		}
		public String getInitialLocation() {
			return initialLocation;
		}
		public void setInitialLocation(String initialLocation) {
			this.initialLocation = initialLocation;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getModifyTime() {
			return modifyTime;
		}
		public void setModifyTime(String modifyTime) {
			this.modifyTime = modifyTime;
		}
		public String getKeyWords() {
			return keyWords;
		}
		public void setKeyWords(String keyWords) {
			this.keyWords = keyWords;
		}
		public String getOpenId() {
			return openId;
		}
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public String getInitalLng() {
			return initalLng;
		}
		public void setInitalLng(String initalLng) {
			this.initalLng = initalLng;
		}
		public String getInitalLat() {
			return initalLat;
		}
		public void setInitalLat(String initalLat) {
			this.initalLat = initalLat;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		
		@Override
		public String toString() {
			return "WxLocationInfo [lId=" + lId + ", initialProvince=" + initialProvince + ", initialCity="
					+ initialCity + ", initialLocation=" + initialLocation + ", initalLng=" + initalLng + ", initalLat="
					+ initalLat + ", province=" + province + ", city=" + city + ", location=" + location + ", lng="
					+ lng + ", lat=" + lat + ", modifyTime=" + modifyTime + ", keyWords=" + keyWords + ", openId="
					+ openId + "]";
		}
		
		
		
		
		
	
		
		
}
