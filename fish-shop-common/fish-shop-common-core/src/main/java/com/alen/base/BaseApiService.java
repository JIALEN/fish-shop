package com.alen.base;

import com.alen.constants.Constants;
import org.springframework.stereotype.Component;


import lombok.Data;

/**
 *
 *
 * @description: 微服务接口实现该接口可以使用传递参数可以直接封装统一返回结果集
 *
 * @author alen
 * @create 2019-09-13 17:27
 **/
@Data
@Component
public class BaseApiService<T> {

	public com.alen.base.BaseResponse<T> setResultError(Integer code, String msg) {
		return setResult(code, msg, null);
	}

	// 返回错误，可以传msg
	public com.alen.base.BaseResponse<T> setResultError(String msg) {
		return setResult(Constants.HTTP_RES_CODE_500, msg, null);
	}

	// 返回成功，可以传data值
	public com.alen.base.BaseResponse<T> setResultSuccess(T data) {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
	}

	// 返回成功，沒有data值
	public com.alen.base.BaseResponse<T> setResultSuccess() {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
	}

	// 返回成功，沒有data值
	public com.alen.base.BaseResponse<T> setResultSuccess(String msg) {
		return setResult(Constants.HTTP_RES_CODE_200, msg, null);
	}

	// 通用封装
	public com.alen.base.BaseResponse<T> setResult(Integer code, String msg, T data) {
		return new com.alen.base.BaseResponse<T>(code, msg, data);
	}

	// 调用数据库层判断
	public Boolean toDaoResult(int result) {
		return result > 0 ? true : false;
	}

}
