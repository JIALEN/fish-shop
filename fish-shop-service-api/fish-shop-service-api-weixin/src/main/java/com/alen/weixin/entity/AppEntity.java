package com.alen.weixin.entity;

import lombok.Data;

/**
 * 实体
 *
 * @author alen
 * @create 2019-09-13 17:09
 **/
@Data
public class AppEntity {

    /**
     * 应用id
     */
    private String appId;
    /**
     * 应用密钥
     */
    private String appSecret;

    public AppEntity() {

    }

    public AppEntity(String appId, String appSecret) {
        super();
        this.appId = appId;
        this.appSecret = appSecret;
    }

}
