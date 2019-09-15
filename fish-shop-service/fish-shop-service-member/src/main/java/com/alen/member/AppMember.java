package com.alen.member;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//开启Swagger生成文件
@EnableSwagger2Doc
public class AppMember {

	public static void main(String[] args) {
		SpringApplication.run(AppMember.class, args);
	}

}
