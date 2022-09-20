package com.search.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "search_word")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SearchWord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String word;

	private Long count;

	private SearchWord(String word) {
		this.word = word;
	}

	private SearchWord(String word, Long count) {
		this.word = word;
		this.count = count;
	}

	public static SearchWord init(String searchWord) {
		return new SearchWord(getWordExcludeUrl(searchWord), 1L);
	}

	private static String getWordExcludeUrl(String searchWord) {
		String[] searchWords = searchWord.split(" ");
		if (searchWords.length > 1) {
			return searchWords[1];
		}

		return searchWords[0];
	}
}
