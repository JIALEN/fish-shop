package com.alen.member.feign;

import com.alen.member.service.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-fish-member")
public interface MemberLoginServiceFeign extends MemberLoginService {

}
