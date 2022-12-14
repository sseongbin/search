package com.search.web.api;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.search.event.SearchEvent;
import com.search.reader.remote.kakao.KakaoSearchClient;
import com.search.reader.remote.kakao.KakaoSearchResult;
import com.search.web.api.rqrs.SearchResultRs;
import com.search.web.api.rqrs.SearchRq;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchResultReadService {

	private final KakaoSearchClient kakakoSearchClient;
	private final ApplicationEventPublisher eventPublisher;

	public SearchResultRs find(SearchRq rq) {
		KakaoSearchResult kakaoSearchResult = kakakoSearchClient.read(rq.getQuery(),
			Optional.ofNullable(rq.getSort())
				.map(Enum::name)
				.orElse(null),
			rq.getPage(),
			rq.getSize());

		eventPublisher.publishEvent(new SearchEvent(rq.getQuery()));

		return SearchResultRs.from(kakaoSearchResult);
	}
}
