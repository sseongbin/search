package com.search.reader.remote.naver;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class NaverSearchResult {
	private Integer total;
	private Integer start;
	private Integer display;
	private List<Item> items;

	@Getter
	public static class Item {
		private String title;
		private String link;
		private String description;
		private String bloggername;
		private ZonedDateTime postdate;
	}
}
