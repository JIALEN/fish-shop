package com.alen.zuul.handler.factory;


import com.alen.core.utils.SpringContextUtil;
import com.alen.zuul.handler.GatewayHandler;
import com.alen.zuul.handler.impl.BlacklistHandler;
import com.alen.zuul.handler.impl.CurrentLimitHandler;

/**
 * 工厂Handler
 *
 * @author alen
 * @create 2019-10-14 10:22
 **/
public class FactoryHandler {

	public static GatewayHandler getHandler() {
		 //1.限流
		GatewayHandler handler1 = (GatewayHandler) SpringContextUtil.getBean("currentLimitHandler");
       // 2.黑名单拦截
		GatewayHandler handler2 = (GatewayHandler) SpringContextUtil.getBean("blacklistHandler");
		handler1.setNextHandler(handler2);
		// 3.验证accessToken
		GatewayHandler handler3 = (GatewayHandler) SpringContextUtil.getBean("apiAuthorityHandler");
		handler2.setNextHandler(handler3);
		// 4.API接口参数接口验签
		GatewayHandler handler4 = (GatewayHandler) SpringContextUtil.getBean("toVerifyMapHandler");
		handler3.setNextHandler(handler4);
		return handler1;
	}

}
