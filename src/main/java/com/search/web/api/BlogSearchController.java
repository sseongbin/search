package com.search.web.api;

import static java.util.stream.Collectors.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.web.api.rqrs.SearchResultRs;
import com.search.web.api.rqrs.SearchRq;
import com.search.web.api.rqrs.SearchWordRs;
import com.search.web.service.SearchWordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/blog/search")
@RequiredArgsConstructor
public class BlogSearchController {

	private final SearchResultReadService searchResultReadService;
	private final SearchWordService searchWordService;

	@GetMapping
	public SearchResultRs search(@Valid SearchRq rq) {
		return searchResultReadService.find(rq);
	}

	@GetMapping("/popular-search-words")
	public List<SearchWordRs> readPopularSearchWords() {
		return searchWordService.findPopularSearchTerms()
			.stream()
			.map(SearchWordRs::create)
			.collect(toList());
	}
}
