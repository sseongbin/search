package com.search.reader.remote.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.reader.remote.kakao.config.KakaoHeaderConfiguration;

@FeignClient(
	name = "kakaoSearchClient",
	url = "https://dapi.kakao.com",
	configuration = KakaoHeaderConfiguration.class,
	fallbackFactory = KakaoSearchClientFallbackFactory.class
)
public interface KakaoSearchClient {
	@GetMapping(value = "/v2/search/blog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	KakaoSearchResult read(
		@RequestParam(value = "query") String query,
		@RequestParam(value = "sort", defaultValue = "accuracy", required = false) String sort,
		@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
		@RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

}
