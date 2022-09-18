package com.search.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.web.api.rqrs.SearchResultRs;
import com.search.web.api.rqrs.SearchRq;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blog/search")
@RequiredArgsConstructor
public class BlogSearchController {

	private final SearchResultReadService searchResultReadService;

	@GetMapping
	public SearchResultRs search(SearchRq rq) {
		return searchResultReadService.find(rq);
	}
}
