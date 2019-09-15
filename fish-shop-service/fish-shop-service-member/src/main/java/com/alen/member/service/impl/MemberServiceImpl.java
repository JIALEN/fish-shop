package com.alen.member.service.impl;

import com.alen.member.fegin.AppServiceFeign;
import com.alen.member.service.MemberService;
import com.alen.weixin.entity.AppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * 会员服务接口实现
 *
 * @author alen
 * @create 2019-09-13 17:46
 **/
@RestController
public class MemberServiceImpl implements MemberService {
	@Autowired
	private AppServiceFeign appServiceFeign;

	@Override
	public AppEntity memberInvokWeixin() {
		return appServiceFeign.getApp();
	}

}
