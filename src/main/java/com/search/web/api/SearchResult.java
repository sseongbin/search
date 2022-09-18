package com.search.web.api;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class SearchResult {
	private List<Document> documents;
	private Meta meta;

	@Getter
	public static class Document {
		private String title;

		private String contents;

		private String url;

		private String blogname;

		private String thumbnail;

		private ZonedDateTime datetime;
	}

	@Getter
	public static class Meta {
		private Integer total_count;

		private Integer pageable_count;

		private Boolean is_end;
	}
}
