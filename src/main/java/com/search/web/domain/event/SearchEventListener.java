package com.search.web.domain.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.search.web.domain.SearchWordService;

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
