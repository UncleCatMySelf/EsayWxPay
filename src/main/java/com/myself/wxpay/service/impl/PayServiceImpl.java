package com.myself.wxpay.service.impl;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.myself.wxpay.Config.AppConstant;
import com.myself.wxpay.bean.*;
import com.myself.wxpay.service.PayService;
import com.myself.wxpay.util.MathUtil;
import com.myself.wxpay.util.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:48 2018\7\29 0029
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "墨书快借订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

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
    public PayResponse notify(String notifyData) {
        //1、验证签名
        //2、支付状态
        //3、支付金额
        //4、支付人（下单人 == 支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}",JsonUtil.toJson(payResponse));

        //判断订单是否存在
        //OrderDTO orderDTO = orderMasterService.findOne(payResponse.getOrderId());
        //模拟判断
        OrderDTO orderDTO = new OrderDTO();

        //判断订单是否存在
        if (orderDTO == null){
            log.error("【微信支付】异步通知，订单不存在，orderId={}",payResponse.getOrderId());
            throw new RuntimeException("订单不存在");
        }

        //判断金额是否一致  比较Double与BigDecimal
        if (!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderPayment().doubleValue())){
            log.error("【微信支付】异步通知，订单金额不一致，orderId={}，微信通知金额={}，系统金额={}",payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderPayment());
            throw new RuntimeException("订单不存在");
        }

        //修改订单的支付状态
       // orderMasterService.paid(orderDTO);

        return payResponse;
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
}
