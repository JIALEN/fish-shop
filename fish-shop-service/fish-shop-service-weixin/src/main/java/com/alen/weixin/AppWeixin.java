package com.alen.weixin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author alen
 * @create 2019-09-13 17:41
 **/
@SpringBootApplication
@EnableEurekaClient
//开启Swagger生成文件
@EnableSwagger2Doc
public class AppWeixin {
    public static void main(String[] args) {
        SpringApplication.run(AppWeixin.class,args);
    }
}
