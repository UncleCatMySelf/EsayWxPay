package com.myself.wxpay.util;

import com.myself.wxpay.Config.WXPayConstants;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 14:31 2018\7\28 0028
 */
public class MyDomain implements IWXPayDomain {
    private static DomainInfo domainInfo;

    public MyDomain(){
        domainInfo = new DomainInfo(WXPayConstants.DOMAIN_API,true);
    }

    @Override
    public void report(String domain, long elapsedTimeMillis, Exception ex) {

    }

    @Override
    public DomainInfo getDomain(WXPayConfig config) {
        return domainInfo;
    }

}
