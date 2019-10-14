package com.alen.zuul.handler.factory;


import com.alen.core.utils.SpringContextUtil;
import com.alen.zuul.handler.GatewayHandler;

/**
 * 工厂Handler
 *
 * @author alen
 * @create 2019-10-14 10:22
 **/
public class FactoryHandler {

	public static GatewayHandler getHandler() {
		// 1.黑名单拦截
		GatewayHandler handler1 = (GatewayHandler) SpringContextUtil.getBean("blacklistHandler");
		// 2.验证accessToken
		GatewayHandler handler2 = (GatewayHandler) SpringContextUtil.getBean("apiAuthorityHandler");
		handler1.setNextHandler(handler2);
		// 3.API接口参数接口验签
		GatewayHandler handler3 = (GatewayHandler) SpringContextUtil.getBean("toVerifyMapHandler");
		handler2.setNextHandler(handler3);
		return handler1;
	}

}
