package com.alen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = { "com.alen.product.es" })
public class AppProduct {

	public static void main(String[] args) {
		SpringApplication.run(AppProduct.class, args);
	}

}
