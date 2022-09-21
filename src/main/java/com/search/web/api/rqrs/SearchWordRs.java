package com.search.web.api.rqrs;

import com.search.domain.SearchWord;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchWordRs {
	private String word;
	private Long count;

	public static SearchWordRs create(SearchWord searchWord) {
		return new SearchWordRs(searchWord.getWord(), searchWord.getCount());
	}
}
