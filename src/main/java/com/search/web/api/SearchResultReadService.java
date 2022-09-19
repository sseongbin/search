package com.search.web.api;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.search.web.api.rqrs.SearchResultRs;
import com.search.web.api.rqrs.SearchRq;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchResultReadService {

	private final SearchClient searchClient;

	public SearchResultRs find(SearchRq rq) {
		SearchResult searchResult = searchClient.read(rq.getQuery(),
			Optional.ofNullable(rq.getSort())
				.map(Enum::name)
				.orElse(null),
			rq.getPage(),
			rq.getSize());

		return SearchResultRs.from(searchResult);
	}
}
