package com.alen.zuul.handler;

import com.alen.zuul.handler.factory.FactoryHandler;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 责任链模式执行
 * @author alen
 * @create 2019-10-14 10:22
 **/
@Component
public class ResponsibilityClient {
	public void responsibility(RequestContext ctx, String ipAddres, HttpServletRequest request,
			HttpServletResponse response) {
		GatewayHandler handler = FactoryHandler.getHandler();
		handler.service(ctx, ipAddres, request, response);
	}

}
