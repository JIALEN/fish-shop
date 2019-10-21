package com.alen.zuul.handler.impl;

import com.alen.core.token.GenerateToken;
import com.alen.zuul.handler.BaseHandler;
import com.alen.zuul.handler.GatewayHandler;
import com.google.common.util.concurrent.RateLimiter;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 服务限流
 *
 * @author alen
 * @create 2019-10-14 10:22
 **/
@Component
@Slf4j
public class CurrentLimitHandler extends BaseHandler implements GatewayHandler {
	private RateLimiter rateLimiter = RateLimiter.create(1);

	@Autowired
	private GenerateToken generateToken;

	@Override
	public Boolean service(RequestContext ctx, String ipAddres,HttpServletRequest req, HttpServletResponse response) {
		// 1.用户限流频率设置 每秒中限制1个请求
		boolean tryAcquire = rateLimiter.tryAcquire(0, TimeUnit.SECONDS);
		if (!tryAcquire) {
			resultError(500, ctx, "现在抢购的人数过多，请稍等一下下哦！");
			return Boolean.FALSE;
		}
		// 2.使用redis限制用户访问频率
		String seckillId = req.getParameter("seckillId");
		String seckillToken = generateToken.getListKeyToken(seckillId + "");
		if (StringUtils.isEmpty(seckillToken)) {
			log.info(">>>seckillId:{}, 亲，该秒杀已经售空，请下次再来!", seckillId);
			resultError(500, ctx, "亲，该秒杀已经售空，请下次再来!");
			return Boolean.FALSE;
		}
		// 3.执行修改库存操作
		if(nextGatewayHandler==null){
			return true;
		}else{
			// 传递给下一个
			nextGatewayHandler.service(ctx, ipAddres, req, response);
		}
		return Boolean.TRUE;
	}

}
