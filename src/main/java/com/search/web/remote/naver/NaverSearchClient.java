package com.search.web.remote.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.web.remote.naver.config.NaverHeaderConfiguration;

@FeignClient(name = "naverSearchClient", url = "https://openapi.naver.com", configuration = NaverHeaderConfiguration.class)
public interface NaverSearchClient {
	@GetMapping(value = "/v1/search/blog.json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	NaverSearchResult read(
		@RequestParam(value = "query") String query,
		@RequestParam(value = "sort", defaultValue = "sim", required = false) String sort,
		@RequestParam(value = "start", defaultValue = "1", required = false) Integer page,
		@RequestParam(value = "display", defaultValue = "10", required = false) Integer size);

}
