package com.search.web.remote.kakao;

import static java.util.stream.Collectors.*;

import java.time.ZonedDateTime;
import java.util.List;

import com.search.web.remote.naver.NaverSearchResult;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoSearchResult {
	private List<Document> documents;
	private Meta meta;

	public static KakaoSearchResult from(NaverSearchResult naverSearchResult) {
		return new KakaoSearchResult(
			naverSearchResult.getItems()
				.stream()
				.map(Document::create)
				.collect(toList()),
			new Meta(naverSearchResult.getTotal(),
				naverSearchResult.getDisplay(),
				naverSearchResult.getTotal() <= naverSearchResult.getStart() + naverSearchResult.getDisplay())
		);
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Document {
		private String title;

		private String contents;

		private String url;

		private String blogname;

		private String thumbnail;

		private ZonedDateTime datetime;

		private static Document create(NaverSearchResult.Item item) {
			return new Document(item.getTitle(),
				item.getDescription(),
				item.getLink(),
				item.getBloggername(),
				null,
				item.getPostdate());
		}
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Meta {
		private Integer total_count;

		private Integer pageable_count;

		private Boolean is_end;
	}
}
