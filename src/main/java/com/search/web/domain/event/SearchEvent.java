package com.search.web.domain.event;

import com.search.common.DomainEvent;

public class SearchEvent extends DomainEvent<String> {
	public SearchEvent(String source) {
		super(source);
	}
}
