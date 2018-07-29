package com.myself.wxpay.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:47 2018\7\29 0029
 */
@Data
public class OrderDTO {
    /**订单id生成*/
    private String orderId;

    /**快递单号*/
    private String expressNum;

    /**买家名称*/
    private String buyerName;

    /**买家电话*/
    private String buyerPhone;

    /**买家地址*/
    private String buyerAddress;

    /**买家Openid*/
    private String buyerOpenid;

    /**实际付款*/
    private BigDecimal orderPayment;

    private List<OrderDetail> orderDetailList;

    /**订单状态*/
    private Integer orderStatus;

    /**支付状态*/
    private Integer payStatus;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String expressNum, String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid, BigDecimal orderPayment, List<OrderDetail> orderDetailList, Integer orderStatus, Integer payStatus, Date createTime, Date updateTime) {
        this.orderId = orderId;
        this.expressNum = expressNum;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.buyerOpenid = buyerOpenid;
        this.orderPayment = orderPayment;
        this.orderDetailList = orderDetailList;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
