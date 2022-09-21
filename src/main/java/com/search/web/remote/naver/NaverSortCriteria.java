package com.search.web.remote.naver;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import com.search.web.remote.kakao.KakaoSortCriteria;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaverSortCriteria {
	sim(KakaoSortCriteria.accuracy.name()),
	date(KakaoSortCriteria.recency.name());

	private static final Map<String, NaverSortCriteria> map = Arrays.stream(values())
		.collect(toMap(NaverSortCriteria::getSortCriteria, Function.identity()));
	private final String sortCriteria;

	public static NaverSortCriteria getBySortCriteria(String name) {
		return map.getOrDefault(name, sim);
	}
}
