package com.alen.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alen.base.BaseResponse;
import com.alen.base.BaseWebController;
import com.alen.member.feign.MemberServiceFeign;
import com.alen.member.output.dto.UserOutDTO;
import com.alen.web.utils.CookieUtils;

@Controller
public class IndexController extends BaseWebController {
	@Autowired
	private MemberServiceFeign memberServiceFeign;
	/**
	 * 跳转到index页面
	 */
	private static final String INDEX_FTL = "index";

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 1.从cookie 中 获取 会员token
		String token = CookieUtils.getCookieValue(request, "meite_token", true);
		if (!StringUtils.isEmpty(token)) {
			// 2.调用会员服务接口,查询会员用户信息
			BaseResponse<UserOutDTO> userInfo = memberServiceFeign.getInfo(token);
			if (isSuccess(userInfo)) {
				UserOutDTO data = userInfo.getData();
				if (data != null) {
					String mobile = data.getMobile();
					String desensMobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
					model.addAttribute("desensMobile", desensMobile);
				}

			}

		}
		return INDEX_FTL;
	}

}
