package com.alen.zuul.handler;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 网关处理接口
 * @author alen
 * @create 2019-10-14 10:22
 **/
public interface GatewayHandler {
	/**
	 * 网关拦截处理请求
	 */
	Boolean service(RequestContext ctx, String ipAddres, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 设置下一个
	 */
	void setNextHandler(GatewayHandler gatewayHandler);

	/**
	 * 获取下一个Handler
	 *
	 * @return
	 */
	public GatewayHandler getNextHandler();
}
