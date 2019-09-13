package com.alen.fegin;

import com.alen.service.entity.AppEntity;
import com.alen.service.service.AppService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * fegin调用微信服务
 *
 * @author alen
 * @create 2019-09-13 17:46
 **/
@FeignClient(name = "app-mayikt-weixin")
public interface AppServiceFeign extends AppService {

}
