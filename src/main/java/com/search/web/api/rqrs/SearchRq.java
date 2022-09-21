package com.search.web.api.rqrs;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.search.common.validation.ValidEnum;
import com.search.web.remote.kakao.KakaoSortCriteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchRq {
	@NotNull(message = "검색어는 필수 입니다.")
	private String query;

	@ValidEnum(enumClass = KakaoSortCriteria.class, message = "정렬 기준은 accuracy 또는 recency 입니다.")
	private KakaoSortCriteria sort;

	@Range(min = 1, max = 50, message = "페이지는 1~50 사이의 값을 입력해야 합니다.")
	private Integer page;

	@Range(min = 1, max = 50, message = "사이즈는 1~50 사이의 값을 입력해야 합니다.")
	private Integer size;
}
