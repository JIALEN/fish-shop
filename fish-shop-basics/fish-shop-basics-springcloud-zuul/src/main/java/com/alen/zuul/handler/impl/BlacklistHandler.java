package com.alen.zuul.handler.impl;


import com.alen.zuul.handler.BaseHandler;
import com.alen.zuul.handler.GatewayHandler;
import com.alen.zuul.mapper.BlacklistMapper;
import com.alen.zuul.mapper.entity.MeiteBlacklist;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 黑名单Handler
 *
 * @author alen
 * @create 2019-10-14 10:22
 **/
@Component
@Slf4j
public class BlacklistHandler extends BaseHandler implements GatewayHandler {

	@Autowired
	private BlacklistMapper blacklistMapper;

	@Override
	public Boolean service(RequestContext ctx, String ipAddres, HttpServletRequest request,
			HttpServletResponse response) {
		// >>>>>>>>>>>>>黑名单拦截操作<<<<<<<<<<<<<<<<<<<
		log.info(">>>>>>>>>拦截1 黑名单拦截 ipAddres:{}<<<<<<<<<<<<<<<<<<<<<<<<<<", ipAddres);
		MeiteBlacklist meiteBlacklist = blacklistMapper.findBlacklist(ipAddres);
		if (meiteBlacklist != null) {
			resultError(ctx, "ip:" + ipAddres + ",Insufficient access rights");
			return Boolean.FALSE;
		}
		// 传递给下一个
		if(nextGatewayHandler==null){
			return true;
		}else{
			nextGatewayHandler.service(ctx, ipAddres, request, response);
		}
		return Boolean.TRUE;
	}

}
