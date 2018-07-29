package com.myself.wxpay.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:23 2018\7\28 0028
 */
@Data
public class WXRequest {

    private String body;

    private String outTradeNo;

    private String feeType = "CNY";

    private BigDecimal totalFee;

    private String notifyUrl;

    private String tradeType = "JSAPI";

    private String openId;

}
