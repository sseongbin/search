package com.search.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class HeaderConfiguration {

	private final static String KAKAO_API_KEY_PREFIX = "KakaoAK ";

	@Value("${api-key.kakao}")
	private String apiKey;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> requestTemplate.header("Authorization", KAKAO_API_KEY_PREFIX + apiKey);
	}
}
