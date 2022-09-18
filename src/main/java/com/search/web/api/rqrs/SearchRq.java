package com.search.web.api.rqrs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchRq {
	private String query;

	private String sort;

	private Integer page;

	private Integer size;
}
