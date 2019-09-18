package com.alen.member.controller;

import javax.servlet.http.HttpSession;

import com.alen.base.BaseResponse;
import com.alen.base.BaseWebController;
import com.alen.bean.utils.MeiteBeanUtils;
import com.alen.constants.Constants;
import com.alen.member.feign.MemberRegisterServiceFeign;
import com.alen.member.input.dto.UserInpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;

import com.alen.member.vo.RegisterVo;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 *
 * @description: 注册请求
 *
 * @author alen
 * @create 2019-09-13 17:46
 **/
@Controller
@Slf4j
public class RegisterController extends BaseWebController {
	private static final String MB_REGISTER_FTL = "member/register";
	/**
	 * 跳转到登陆页面页面
	 */
	private static final String MB_LOGIN_FTL = "member/login";
	@Autowired
	private MemberRegisterServiceFeign memberRegisterServiceFeign;

	/**
	 * 跳转到注册页面
	 *
	 * @return
	 */
	@GetMapping("/register")
	public String getRegister() {
		return MB_REGISTER_FTL;
	}

	/**
	 * 跳转到注册页面
	 *
	 * @return
	 */
	@PostMapping("/register")
	public String postRegister(@ModelAttribute("registerVo") @Validated RegisterVo registerVo,
			BindingResult bindingResult, HttpSession httpSession, Model model) {
		// 1.参数验证
		if (bindingResult.hasErrors()) {
			// 获取第一个错误!
			String errorMsg = bindingResult.getFieldError().getDefaultMessage();
			setErrorMsg(model, errorMsg);
			return MB_REGISTER_FTL;
		}
		// 将VO转换DTO
		UserInpDTO voToDto = MeiteBeanUtils.voToDto(registerVo, UserInpDTO.class);
		try {
			String registCode = registerVo.getRegistCode();
			BaseResponse<JSONObject> register = memberRegisterServiceFeign.register(voToDto, registCode);
			if (!register.getCode().equals(Constants.HTTP_RES_CODE_200)) {
				model.addAttribute("error", register.getMsg());
				return MB_REGISTER_FTL;
			}
		} catch (Exception e) {
			log.error(">>>>>", e);
			model.addAttribute("error", "系统出现错误!");
			return MB_REGISTER_FTL;
		}

		// 注册成功,跳转到登陆页面
		return MB_LOGIN_FTL;
	}

}
