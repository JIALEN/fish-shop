package com.alen.weixin.service;

import com.alen.weixin.entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 微信接口
 *
 * @author alen
 * @create 2019-09-13 17:27
 **/
public interface AppService {
    /**
     * 获取app应用信息
     *
     * @return
     */
    @GetMapping("/getApp")
    public AppEntity getApp();
}
