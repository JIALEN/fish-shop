package com.alen.spike.service.impl;

import com.alen.api.spike.service.SpikeCommodityService;
import com.alen.base.BaseApiService;
import com.alen.base.BaseResponse;
import com.alen.core.token.GenerateToken;
import com.alen.spike.producer.SpikeCommodityProducer;
import com.alen.spike.service.mapper.SeckillMapper;
import com.alen.spike.service.mapper.entity.SeckillEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SpikeCommodityServiceImpl extends BaseApiService<JSONObject> implements SpikeCommodityService {
	@Autowired
	private SeckillMapper seckillMapper;

	@Autowired
	private GenerateToken generateToken;

	@Autowired
	private SpikeCommodityProducer spikeCommodityProducer;

	@Override
	@Transactional
	public BaseResponse<JSONObject> spike(String phone, Long seckillId) {
		// 1.参数验证
		if (StringUtils.isEmpty(phone)) {
			return setResultError("手机号码不能为空!");
		}
		if (seckillId == null) {
			return setResultError("商品库存id不能为空!");
		}

		// 2.用户频率限制 setnx 如果key存在话
		// Boolean reusltNx = redisUtil.setNx(phone, seckillId + "", 10l);
		// if (!reusltNx) {
		// return setResultError("访问次数过多，10秒后在实现重试!");
		// }
		// 3.修改数据库对应的库存 1万中只有100个抢购成功 提前生成好100个token 谁能够抢购成功token放入到mq中实现异步修改库存
		String seckillToken = generateToken.getListKeyToken(seckillId + "");
		if (StringUtils.isEmpty(seckillToken)) {
			return setResultError("商品库存已经售空啦！请关注下一次机会哦！");
		}
		addMQSpike(seckillId, phone);

		return setResultSuccess("正在排队中.....");
	}

	@Async
	public void addMQSpike(Long seckillId, String phone) {
		JSONObject data = new JSONObject();
		data.put("seckillId", seckillId);
		data.put("phone", phone);
		spikeCommodityProducer.send(data);
	}

	@Override
	public BaseResponse<JSONObject> addSpikeToken(Long seckillId, Long tokenQuantity) {
		// 1.验证参数
		if (seckillId == null) {
			return setResultError("商品库存id不能为空!");
		}
		if (tokenQuantity == null) {
			return setResultError("token数量不能为空!");
		}
		SeckillEntity seckillEntity = seckillMapper.findBySeckillId(seckillId);
		if (seckillEntity == null) {
			return setResultError("商品信息不存在!");
		}
		// 2.使用多线程异步生产令牌
		createSeckillToken(seckillId, tokenQuantity);
		return setResultSuccess("令牌正在生成中.....");
	}

	@Async
	private void createSeckillToken(Long seckillId, Long tokenQuantity) {
		generateToken.createListToken("seckill_", seckillId + "", tokenQuantity);
	}

}
