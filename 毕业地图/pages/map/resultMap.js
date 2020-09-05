// pages/map/resultMap.js
var amapFile = require('../../utils/amap-wx.js');
var lat;  //经度
var lng; //纬度
var screenWidth; //屏幕宽度
var screenHeight; //屏幕高度
var province;
var qqMapWx = require('../../utils/qqmap-wx-jssdk.js');
var demo = new qqMapWx({
  key: 'QQPBZ-WHSKU-K3GV5-4RHYN-6D56S-FTBSQ'
});

Page({

  /**
   * 保存图片
   
  save:function(e){
    //console.log("保存");
    const ctx = wx.createCanvasContext('myCanvas');
    ctx.draw(
      true, setTimeout(function () {
        wx.canvasToTempFilePath({
          x: 0,
          y: 0,
          width: screenWidth,
          height: screenHeight,
          destWidth: screenWidth,
          destHeight: screenHeight,
          canvasId: 'myCanvas',
          success: function (res) {
            console.log(res.tempFilePath)  // 返回图片路径
            wx.saveImageToPhotosAlbum({
              filePath: res.tempFilePath,
            })
          }
        }, this)
      }, 100)
    );
  },
*/
  /**
   * 页面的初始数据
   */
  data: {
    latitude: '',
    longitude: '',
    markers: [],
    textData:{},
    canvaswidth:'',
    canvasheight:'',
    src:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var that = this;
    var result_str = options.result;
    var result_json = JSON.parse(result_str);
    console.log(result_json);
    
    province = result_json.result.address_components.province;
    lat = result_json.result.location.lat;
    lng = result_json.result.location.lng;
    var marker = [{
      latitude: result_json.result.location.lat,
      longitude: result_json.result.location.lng
    }];
    if (province == "北京市" || province == "天津市" || province == "上海市" || province == "重庆市") {
      that.setData({
        textData: {
          province: result_json.result.address_components.province
        }
      })
    }
    else {
      that.setData({
        textData: {
          province: result_json.result.address_components.province,
          city: result_json.result.address_components.city
        }
      })
    }
    that.setData({
      markers: marker
    });
    that.setData({
      latitude: result_json.result.location.lat
    });
    that.setData({
      longitude: result_json.result.location.lng
    });

    var that = this;
    var myAmapFun = new amapFile.AMapWX({ key: "749bad8fcd28e84d358cb96942af0efa" });

    wx.getSystemInfo({
      success: function(res) {
        screenWidth = res.screenWidth;
        screenHeight = res.screenHeight*0.75;
        that.setData({
          canvaswidth: res.screenWidth,
          canvasheight: res.screenHeight*0.75
        })

        var size = res.screenWidth + "*" + res.screenHeight;

        myAmapFun.getStaticmap({
          location:lng+','+lat,
          zoom: 3,
          size: size,
          scale: 2,
          markers: "mid,0xFF0000,A:" + lng + "," + lat,
          success: function (data) {
            console.log(data);
            that.setData({
              src: data.url
            })
          },
          fail: function (info) {
            wx.showModal({ title: info.errMsg })
          }
        })

      },
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