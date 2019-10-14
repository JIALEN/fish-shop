package com.alen.zuul.handler.impl;


import com.alen.sign.SignUtil;
import com.alen.zuul.handler.BaseHandler;
import com.alen.zuul.handler.GatewayHandler;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 参数验证
 * MD5 单向加密 不可逆 加盐
 * 	 客户端调用接口 add?userName=yushengjun&zhangsan=644 MD5
 * 	 userName=yushengjun&zhangsan=644 ==签名=msfgfjsjsxjss
 * 	 userName=yushengjun&zhangsan=644 名=msfgfjsjsxjss
 * 	 msfgfjsjsxjss=msfgfjsjsxjss
 *
 * 	 签名的目的是 为了防止数据被篡改 数据还是明文数据
 * 	 加密 RSA
 * @author alen
 * @create 2019-10-14 10:22
 **/
@Component
@Slf4j
public class ToVerifyMapHandler extends BaseHandler implements GatewayHandler {

	@Override
	public Boolean service(RequestContext ctx, String ipAddres, HttpServletRequest request,
			HttpServletResponse response) {
		log.info(">>>>>>>>>拦截3 参数验证<<<<<<<<<<<<<<<<<<<<<<<<");
		Map<String, String> verifyMap = SignUtil.toVerifyMap(request.getParameterMap(), false);
		if (!SignUtil.verify(verifyMap)) {
			resultError(ctx, "ip:" + ipAddres + ",Sign fail");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}
