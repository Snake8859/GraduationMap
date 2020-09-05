// pages/map/map.js
//引入amap-wx.js
var amapFile = require('../../utils/amap-wx.js');
var address;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    markers: [],
    latitude: '',
    longitude: '',
    textData: {}
  },
  getAddr:function(e){
    //console.log(e.detail.value);
    address=e.detail.value.addr;
    //利用腾讯地图的Webservice接口获得经度和纬度
    //腾讯地图的WebService接口是HHTPS请求的
    wx.request({
      url: 'https://apis.map.qq.com/ws/geocoder/v1/?address=' + address + '&key=QQPBZ-WHSKU-K3GV5-4RHYN-6D56S-FTBSQ',
      method: 'GET',
      dataTypeL: 'JSON',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        //console.log(res);
        var result =JSON.stringify(res.data);
        var status = res.data.status;
        if (status == '347') {
          wx.showModal({
            title: '提示',
            content: '查询无结果',
          })
        }
        if (status == '306') {
          wx.showModal({
            title: '提示',
            content: '输入内容不能为空',
          })
        }
        if(status=='0'){
        wx.navigateTo({
          url: '../map/resultMap?result=' + result
        })
        }
      },
      fail: function (res) {
        console.log("输出错误内容" + res);
      },
    })

    //将地址传递到下一个页面
   
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
   //高德地图 -- 逆地址解析
    var that = this;
    var myAmapFun = new amapFile.AMapWX({ key: '749bad8fcd28e84d358cb96942af0efa' });

    myAmapFun.getRegeo({
      //图标设计
      iconPath: "../../images/marker.png",
      iconWidth: 22,
      iconHeight: 32,
      success: function (data) {
        //成功回调
        console.log(data);
        var marker = [{
          id: data[0].id,
          latitude: data[0].latitude,
          longitude: data[0].longitude,
          iconPath: data[0].iconPath,
          width: data[0].width,
          height: data[0].height
        }]
        that.setData({
          markers: marker
        });
        that.setData({
          latitude: data[0].latitude
        });
        that.setData({
          longitude: data[0].longitude
        });
        that.setData({
          textData: {
            name: data[0].name,
            desc: data[0].desc
          }
        })
      },
      fail: function (info) {
        //失败回调
        console.log(info)
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})