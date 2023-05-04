package com.shpapth.githubrepolister.githubclient.config;

import com.shpapth.githubrepolister.githubclient.interceptor.GithubSearchQueryParamDecoderInterceptor;
import org.springframework.context.annotation.Bean;

public class GithubFeignConfig {

	@Bean
	public GithubSearchQueryParamDecoderInterceptor githubRequestInterceptor() {
		return new GithubSearchQueryParamDecoderInterceptor();
	}
}
