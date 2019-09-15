package com.alen.weixin.service.impl;

import com.alen.weixin.entity.AppEntity;
import com.alen.weixin.service.AppService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信接口实现
 *
 * @author alen
 * @create 2019-09-13 17:38
 **/
@RestController
public class AppServiceImpl implements AppService {
    /**
     * 获取app应用信息
     *
     * @return
     */
    @Override
    public AppEntity getApp() {
        return new AppEntity("123456", "test");
    }
}
