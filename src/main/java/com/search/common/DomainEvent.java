package com.search.common;

import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DomainEvent<T> {
	private T source;
	private ZonedDateTime dateTime;

	protected DomainEvent(T source) {
		this.source = source;
		this.dateTime = ZonedDateTime.now();
	}
}
