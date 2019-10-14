package com.alen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证启动
 *
 * @author alen
 * @create 2019-10-14 10:22
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
// @EnableApolloConfig
@MapperScan(basePackages = "com.alen.auth.mapper")
public class AppAuth {

	public static void main(String[] args) {
		SpringApplication.run(AppAuth.class, args);
	}

}
