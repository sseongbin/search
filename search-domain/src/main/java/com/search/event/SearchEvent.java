package com.search.event;

public class SearchEvent extends DomainEvent<String> {
	public SearchEvent(String source) {
		super(source);
	}
}
