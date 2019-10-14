package com.alen.zuul.feign;

import com.alen.auth.service.api.AuthorizationService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-fish-auth")
public interface AuthorizationServiceFeign extends AuthorizationService {

}
