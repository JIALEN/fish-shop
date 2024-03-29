package com.alen.zuul.handler;

import com.alen.base.BaseResponse;
import com.alen.constants.Constants;
import com.netflix.zuul.context.RequestContext;

public class BaseHandler {
	public GatewayHandler nextGatewayHandler;

	public void setNextHandler(GatewayHandler nextGatewayHandler) {
		this.nextGatewayHandler = nextGatewayHandler;
	}
	public GatewayHandler getNextHandler() {
		return nextGatewayHandler;
	}

	protected void resultError(RequestContext ctx, String errorMsg) {
		ctx.setResponseStatusCode(401);
		// 网关响应为false 不会转发服务
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody(errorMsg);
	}
	public void resultError(Integer code, RequestContext ctx, String errorMsg) {
		ctx.setResponseStatusCode(code);
		// 网关响应为false 不会转发服务
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody(errorMsg);
		ctx.getResponse().setContentType("text/html;charset=UTF-8");

	}
	// 接口直接返回true 或者false
	public Boolean isSuccess(BaseResponse<?> baseResp) {
		if (baseResp == null) {
			return false;
		}
		if (!baseResp.getCode().equals(Constants.HTTP_RES_CODE_200)) {
			return false;
		}
		return true;
	}

}
