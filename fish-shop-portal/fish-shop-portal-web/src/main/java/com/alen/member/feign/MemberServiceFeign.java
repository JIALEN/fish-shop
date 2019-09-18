package com.alen.member.feign;

import com.alen.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-fish-member")
public interface MemberServiceFeign extends MemberService {

}
