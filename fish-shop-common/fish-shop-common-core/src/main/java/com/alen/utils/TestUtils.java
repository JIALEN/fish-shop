package com.alen.utils;


import com.alen.sign.SignUtil;

import java.util.HashMap;
import java.util.Map;

public class TestUtils {

	public static void main(String[] args) {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("mobile", "15000536481");
//		sParaTemp.put("orderId", "2019010203501502");
//		sParaTemp.put("userId", "644064");
		System.out.println(SignUtil.sign(sParaTemp));
//		String reuslt = HttpClientUtils.doPost("http://127.0.0.1/api-pay/cratePayToken", SignUtil.sign(sParaTemp));
//		System.out.println("reusltL:" + reuslt);
	}
}
