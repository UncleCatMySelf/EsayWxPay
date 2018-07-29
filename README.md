## 最简单的微信小程序支付工具包

### 配置
在AppConstant下配置自己的小程序信息与商户号
```java
public interface AppConstant {

    Integer SCOPE_USER = 10;

    String CLASSPATH = "支付证书";

    String WXURL = "https://api.weixin.qq.com/sns/jscode2session?";

    String APPID = "小程序appId";

    String APPSECRET = "小程序secret";

    String APPKEY = "appkey";

    String MERCHANT_NUMBER = "商户号";

    String GRANTTYPE = "authorization_code";

    String NOTIFY_URL = "自己项目的回调url";


}
```
### 生成下单对象与退款对象
如PayServiceImpl中配置自己的下单类与退款类即可
```java
@Override
    public WxResponse create(OrderDTO orderDTO) {
        WxResponse wxResponse = new WxResponse();
        WXPayUtil wxPayUtil = new WXPayUtil();
        WXRequest wxRequest = new WXRequest();
        //构造对应自己的下单五个参数即可
        wxRequest.setBody(ORDER_NAME);
        wxRequest.setOpenId(orderDTO.getBuyerOpenid());
        wxRequest.setTotalFee(orderDTO.getOrderPayment());
        wxRequest.setOutTradeNo(orderDTO.getOrderId());
        wxRequest.setNotifyUrl(AppConstant.NOTIFY_URL);
        log.info("【微信支付】发起支付，request={}", JsonUtil.toJson(wxRequest));
        try {
            //调用支付
            wxResponse = wxPayUtil.pay(wxRequest);
        }catch (Exception e){
           e.printStackTrace();
        }

        log.info("【微信支付】发起支付，response={}",JsonUtil.toJson(wxResponse));

        return wxResponse;
    }
    
    @Override
        public WxRefundResponse refund(OrderDTO orderDTO) {
            WxRefundResponse wxRefundResponse = new WxRefundResponse();
            WXPayUtil wxPayUtil = new WXPayUtil();
            WXRefundRequest wxRefundRequest = new WXRefundRequest();
            //构造自己退款的六个参数即可
            wxRefundRequest.setOutTradeNo(orderDTO.getOrderId());
            wxRefundRequest.setNotifyUrl(AppConstant.NOTIFY_URL);
            wxRefundRequest.setRefundFee(orderDTO.getOrderPayment());
            wxRefundRequest.setTotalFee(orderDTO.getOrderPayment());
            wxRefundRequest.setOutRefundNo(WXPayUtil.getOnlyId());
            wxRefundRequest.setRefundFeeType("CNY");
            log.info("【微信退款】发起退款，request={}", JsonUtil.toJson(wxRefundRequest));
            try {
                //请求对应的退款
                wxRefundResponse = wxPayUtil.refund(wxRefundRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("【微信退款】发起退款，response={}", JsonUtil.toJson(wxRefundResponse));
    
            return wxRefundResponse;
        }
```
###小程序调试代码

```js
refund:function(){
    home.refunds((res)=>{
      console.log(res);
    })
  },

  deposit:function(){
    home.deposits((res) => {
      //console.log(res.data);
      wx.requestPayment({
        timeStamp: res.data.timeStamp,
        nonceStr: res.data.nonceStr,
        package: res.data.packAge,
        signType: res.data.signType,
        paySign: res.data.paySign,
        success:function(res){
          wx.showToast({
            title: '充值成功',
          })
        },
        fail:function(res){
          wx.showToast({
            title: '充值失败',
          })
        }
      })
    })
```

###运行程序即可跑通

