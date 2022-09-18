package com.search.web.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.web.config.HeaderConfiguration;

@FeignClient(name = "searchClient", url = "https://dapi.kakao.com", configuration = HeaderConfiguration.class)
public interface SearchClient {
	@GetMapping(value = "/v2/search/blog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	SearchResult read(
		@RequestParam(value = "query") String query,
		@RequestParam(value = "sort", defaultValue = "accuracy", required = false) String sort,
		@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
		@RequestParam(value = "size", defaultValue = "10", required = false) Integer size);

}
