package com.alen.member.service;

import com.alen.base.BaseResponse;
import com.alen.member.input.dto.UserInpDTO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "会员注册接口")
public interface MemberRegisterService {
    /**
     * 用户注册接口
     *
     * @param userInpDTO
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse<JSONObject> register(@RequestBody UserInpDTO userInpDTO);

}
