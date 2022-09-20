package com.search.web.domain.event;

import com.search.common.event.DomainEvent;

public class SearchEvent extends DomainEvent<String> {
	public SearchEvent(String source) {
		super(source);
	}
}
