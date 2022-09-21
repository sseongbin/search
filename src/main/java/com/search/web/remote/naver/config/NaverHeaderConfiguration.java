package com.search.web.remote.naver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class NaverHeaderConfiguration {

	@Value("${api-key.naver.client.id}")
	private String clientId;

	@Value("${api-key.naver.client.secret}")
	private String clientSecret;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> requestTemplate.header("X-Naver-Client-Id", clientId)
			.header("X-Naver-Client-Secret", clientSecret);
	}
}
