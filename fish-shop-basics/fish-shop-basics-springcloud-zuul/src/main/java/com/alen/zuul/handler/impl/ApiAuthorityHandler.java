package com.alen.zuul.handler.impl;

import com.alen.base.BaseResponse;
import com.alen.zuul.feign.AuthorizationServiceFeign;
import com.alen.zuul.handler.BaseHandler;
import com.alen.zuul.handler.GatewayHandler;
import com.alibaba.fastjson.JSONObject;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ApiAuthorityHandler extends BaseHandler implements GatewayHandler {
	@Autowired
	private AuthorizationServiceFeign verificaCodeServiceFeign;

	@Override
	public Boolean service(RequestContext ctx, String ipAddres, HttpServletRequest request,
			HttpServletResponse response) {
		log.info(">>>>>>>>>拦截2 开放Api接口 Token验证 ipAddres:{}<<<<<<<<<<<<<<<<<<<<<<<<<<", ipAddres);
		if(gatewayHandler==null){
			return true;
		}
		String servletPath = request.getServletPath();
		if (!servletPath.substring(0, 7).equals("/public")) {
			// 传递给下一个
			gatewayHandler.service(ctx, ipAddres, request, response);
			return true;
		}
		String accessToken = request.getParameter("accessToken");
		log.info(">>>>>accessToken验证:" + accessToken);
		if (StringUtils.isEmpty(accessToken)) {
			resultError(ctx, "AccessToken cannot be empty");
			return false;
		}
		// 调用接口验证accessToken是否失效
		BaseResponse<JSONObject> appInfo = verificaCodeServiceFeign.getAppInfo(accessToken);
		log.info(">>>>>>data:" + appInfo.toString());
		if (!isSuccess(appInfo)) {
			resultError(ctx, appInfo.getMsg());
			return Boolean.FALSE;
		}
		// 传递给下一个
		gatewayHandler.service(ctx, ipAddres, request, response);
		return Boolean.TRUE;
	}

}
