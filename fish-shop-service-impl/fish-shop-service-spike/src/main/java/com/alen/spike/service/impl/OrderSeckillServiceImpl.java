package com.alen.spike.service.impl;

import com.alen.api.spike.service.OrderSeckillService;
import com.alen.base.BaseApiService;
import com.alen.base.BaseResponse;
import com.alen.spike.service.mapper.OrderMapper;
import com.alen.spike.service.mapper.entity.OrderEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
public class OrderSeckillServiceImpl extends BaseApiService<JSONObject> implements OrderSeckillService {
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public BaseResponse<JSONObject> getOrder(String phone, Long seckillId) {
		if (StringUtils.isEmpty(phone)) {
			return setResultError("手机号码不能为空!");
		}
		if (seckillId == null) {
			return setResultError("商品库存id不能为空!");
		}
		OrderEntity orderEntity = orderMapper.findByOrder(phone, seckillId);
		if (orderEntity == null) {
			return setResultError("正在排队中.....");
		}
		return setResultSuccess("恭喜你秒杀成功!");
	}

}
