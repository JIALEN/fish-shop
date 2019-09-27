package com.alen.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *
 *
 * @description: 支付网站
 * @author alen
 * @create 2019-09-13 17:41
 */
@Controller
public class PayController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
