package com.myself.wxpay.service;

import com.lly835.bestpay.model.PayResponse;
import com.myself.wxpay.bean.OrderDTO;
import com.myself.wxpay.bean.WxRefundResponse;
import com.myself.wxpay.bean.WxResponse;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:46 2018\7\29 0029
 */
public interface PayService {
    WxResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    WxRefundResponse refund(OrderDTO orderDTO);
}
