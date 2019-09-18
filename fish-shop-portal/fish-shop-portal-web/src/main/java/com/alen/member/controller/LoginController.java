package com.alen.member.controller;



import com.alen.base.BaseResponse;
import com.alen.base.BaseWebController;
import com.alen.constants.Constants;
import com.alen.core.bean.MeiteBeanUtils;
import com.alen.member.feign.MemberLoginServiceFeign;
import com.alen.member.input.dto.UserLoginInpDTO;
import com.alen.web.utils.CookieUtils;
import com.alen.web.utils.RandomValidateCodeUtil;
import com.alibaba.fastjson.JSONObject;

import com.alen.member.vo.LoginVo;


import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 *
 *
 * @description:登陆请求
 *
 * @author alen
 * @create 2019-09-13 17:46
 **/
@Controller
public class LoginController extends BaseWebController {
	/**
	 * 跳转到登陆页面页面
	 */
	private static final String MB_LOGIN_FTL = "member/login";
	/**
	 * 重定向到首页
	 */
	private static final String REDIRECT_INDEX = "redirect:/";
	@Autowired
	private MemberLoginServiceFeign memberLoginServiceFeign;

	/**
	 * 跳转页面
	 *
	 * @return
	 */
	@GetMapping("/login")
	public String getLogin() {
		return MB_LOGIN_FTL;
	}

	/**
	 * 接受请求参数
	 *
	 * @return
	 */
	@PostMapping("/login")
	public String postLogin(@ModelAttribute("loginVo") LoginVo loginVo, Model model, HttpServletRequest request,
							HttpServletResponse response, HttpSession httpSession) {
		// 1.图形验证码判断
		String graphicCode = loginVo.getGraphicCode();
		if (!RandomValidateCodeUtil.checkVerify(graphicCode, httpSession)) {
			setErrorMsg(model, "图形验证码不正确!");
			return MB_LOGIN_FTL;
		}

		// 2.将vo转换为dto
		UserLoginInpDTO voToDto = MeiteBeanUtils.doToDto(loginVo, UserLoginInpDTO.class);
		voToDto.setLoginType(Constants.MEMBER_LOGIN_TYPE_PC);
		String info = webBrowserInfo(request);
		voToDto.setDeviceInfor(info);
		BaseResponse<JSONObject> login = memberLoginServiceFeign.login(voToDto);
		if (!isSuccess(login)) {
			setErrorMsg(model, login.getMsg());
			return MB_LOGIN_FTL;
		}
		// 2.将token存入到cookie中
		JSONObject data = login.getData();
		String token = data.getString("token");
		CookieUtils.setCookie(request, response, "meite_token", token);
		// 登陆成功后，跳转到首页
		return REDIRECT_INDEX;
	}

	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		// 1. 从cookie中获取token
		String token = CookieUtils.getCookieValue(request, "meite_token");
		if (!StringUtils.isEmpty(token)) {
			BaseResponse<JSONObject> delToken = memberLoginServiceFeign.delToken(token);
			if (isSuccess(delToken)) {
				return REDIRECT_INDEX;
			}
		}

		return ERROR_500_FTL;
	}

	/**
	 * 获取浏览器信息
	 *
	 * @return
	 */
	public String webBrowserInfo(HttpServletRequest request) {
		// 获取浏览器信息
		Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
		// 获取浏览器版本号
		Version version = browser.getVersion(request.getHeader("User-Agent"));
		String info = browser.getName() + "/" + version.getVersion();
		return info;
	}
}
