package com.search.web.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.search.event.SearchEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchEventListener {

	private final SearchWordService searchWordService;

	@EventListener
	public void searchEventCreated(SearchEvent event) {
		searchWordService.increaseCount(event.getSource());
	}
}
