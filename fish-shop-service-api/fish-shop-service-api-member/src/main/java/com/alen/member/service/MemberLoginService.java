package com.alen.member.service;

import com.alen.base.BaseResponse;
import com.alen.member.input.dto.UserLoginInpDTO;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 *
 *
 * @author alen
 * @create 2019-09-13 17:27
 **/
@Api(tags = "用户登陆服务接口")
public interface MemberLoginService {
	/**
	 * 用户登陆接口
	 *
	 * @param userLoginInpDTO
	 * @return
	 */
	@PostMapping("/login")
	@ApiOperation(value = "会员用户登陆信息接口")
	BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);

	/**
	 * 删除登陆token
	 *
	 * @return
	 */
	@PostMapping("/delToken")
	@ApiOperation(value = "删除登陆token")
	BaseResponse<JSONObject> delToken(@RequestParam("token") String token);
}
