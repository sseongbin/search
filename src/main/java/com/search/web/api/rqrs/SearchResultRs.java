package com.search.web.api.rqrs;

import static java.util.stream.Collectors.*;

import java.time.ZonedDateTime;
import java.util.List;

import com.search.web.api.SearchResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchResultRs {
	private List<DocumentRs> documents;
	private MetaRs meta;

	public static SearchResultRs from(SearchResult searchResult) {
		return new SearchResultRs(searchResult.getDocuments()
			.stream()
			.map(DocumentRs::create)
			.collect(toList()),
			MetaRs.create(searchResult.getMeta()));
	}

	@Getter
	@AllArgsConstructor
	static class DocumentRs {
		private String title;
		private String contents;
		private String url;
		private String blogName;
		private String thumbnail;
		private ZonedDateTime datetime;

		public static DocumentRs create(SearchResult.Document document) {
			return new DocumentRs(document.getTitle(),
				document.getContents(),
				document.getUrl(),
				document.getBlogname(),
				document.getThumbnail(),
				document.getDatetime());
		}
	}

	@Getter
	@AllArgsConstructor
	static class MetaRs {
		private Integer totalCount;
		private Integer pageableCount;
		private Boolean isEnd;

		public static MetaRs create(SearchResult.Meta meta) {
			return new MetaRs(meta.getTotal_count(),
				meta.getPageable_count(),
				meta.getIs_end());
		}
	}
}
