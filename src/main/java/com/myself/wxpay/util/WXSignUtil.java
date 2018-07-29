package com.myself.wxpay.util;


import com.myself.wxpay.Config.AppConstant;
import com.myself.wxpay.Config.WXPayConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:07 2018\7\28 0028
 */
public class WXSignUtil {

    /**
     * 生成微信支付接口调用参数值
     * @param resp
     * @return 除去appId
     * @throws Exception
     */
    public static Map<String,String> sign(Map<String,String> resp) throws Exception{
        Map<String,String> result = new HashMap<String,String>();
        result.put("appId", AppConstant.APPID);
        result.put("timeStamp",String.valueOf(WXPayUtil.getCurrentTimestamp()));
        result.put("nonceStr", WXPayUtil.generateNonceStr());
        result.put("package","prepay_id="+resp.get("prepay_id"));
        result.put("signType","HMAC-SHA256");
        String sign = WXPayUtil.generateSignature(result,AppConstant.APPKEY, WXPayConstants.SignType.HMACSHA256);
        result.put("paySign",sign);
        result.remove("appId");
        return result;
    }

}
