package com.alen.api.spike.service;

import com.alen.base.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

/**
 * 查询秒杀记录
 *
 *
 * @author alen
 * @create 2019-09-13 17:27
 **/
public interface OrderSeckillService {

	@RequestMapping("/getOrder")
	 BaseResponse<JSONObject> getOrder(String phone, Long seckillId);

}
