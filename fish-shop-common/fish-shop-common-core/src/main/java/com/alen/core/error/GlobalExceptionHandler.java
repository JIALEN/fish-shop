package com.alen.core.error;

import com.alen.base.BaseApiService;
import com.alen.base.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 *
 * @description: 全局捕获异常
 *
 * @author alen
 * @create 2019-09-13 17:27
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BaseApiService<JSONObject> {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public BaseResponse<JSONObject> exceptionHandler(Exception e) {
		log.info("###全局捕获异常###,error:{}", e);
		return setResultError("系统错误!");
	}
}
