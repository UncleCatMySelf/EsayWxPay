package com.myself.wxpay.bean;

import lombok.Data;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:51 2018\7\28 0028
 */
@Data
public class WxResponse {

    private String timeStamp;

    private String nonceStr;

    private String signType;

    private String packAge;

    private String paySign;

}
