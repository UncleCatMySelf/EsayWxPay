package com.myself.wxpay.Config;

/**
 * 配置自己的支付属性
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:38 2018\7\29 0029
 */
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
