package com.myself.wxpay.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 13:51 2018\7\29 0029
 */
@Data
public class WXRefundRequest {

    private String outTradeNo;

    private String refundFeeType = "CNY";

    private BigDecimal totalFee;

    private BigDecimal refundFee;

    private String notifyUrl;

    private String outRefundNo;

}
