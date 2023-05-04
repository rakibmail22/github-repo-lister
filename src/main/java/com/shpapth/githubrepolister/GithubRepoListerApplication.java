package com.shpapth.githubrepolister;

import feign.Contract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class GithubRepoListerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubRepoListerApplication.class, args);
	}

	@Bean
	public Contract useFeignAnnotations() {
		return new Contract.Default();
	}
}
