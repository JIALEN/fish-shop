package com.alen.member.feign;

import com.alen.member.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;


import feign.Headers;

@FeignClient("app-fish-member")
//@Headers({ "Content-Type: application/json", "Accept: application/json" })
public interface MemberRegisterServiceFeign extends MemberRegisterService {

}
