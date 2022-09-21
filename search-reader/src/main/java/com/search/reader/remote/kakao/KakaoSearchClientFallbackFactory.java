package com.search.reader.remote.kakao;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.search.reader.remote.naver.NaverSearchClient;
import com.search.reader.remote.naver.NaverSearchResult;
import com.search.reader.remote.naver.NaverSortCriteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class KakaoSearchClientFallbackFactory implements FallbackFactory<KakaoSearchClient> {
	private final NaverSearchClient naverSearchClient;

	@Override
	public KakaoSearchClient create(Throwable cause) {
		return (query, sort, page, size) -> {
			log.error("[fallback]] kakao api 서버 에러 {}", cause.getMessage());
			NaverSearchResult naverSearchResult = naverSearchClient.read(query,
				NaverSortCriteria.getBySortCriteria(sort).name(),
				page,
				size);

			return KakaoSearchResult.from(naverSearchResult);
		};
	}
}
