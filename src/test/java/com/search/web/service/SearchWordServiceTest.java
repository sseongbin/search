package com.search.web.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.search.web.domain.SearchWord;
import com.search.web.domain.SearchWordRepository;

@Sql("/db/test-data.sql")
@SpringBootTest
@Transactional
class SearchWordServiceTest {
	@Autowired
	private SearchWordService searchWordService;

	@Autowired
	private SearchWordRepository searchWordRepository;

	@Autowired
	private EntityManager em;

	@Test
	@DisplayName("처음 검색하는 검색어는 카운트가 1로 저장된다.")
	void 검색어_테스트1() {
		// given
		// test-data.sql
		String word = "검색어";

		// when
		searchWordService.increaseCount(word);

		// then
		SearchWord searchWord = searchWordRepository.findByWord(word);
		assertAll(
			() -> assertThat(searchWord.getWord()).isEqualTo(word),
			() -> assertThat(searchWord.getCount()).isEqualTo(1L)
		);
	}

	@Test
	@DisplayName("이미 검색된 단어는 카운트가 1 증가한다.")
	void 검색어_테스트2() {
		// given
		// test-data.sql -> 월요일은 10번 검색
		String word = "월요일";

		// when
		searchWordService.increaseCount(word);
		em.flush();
		em.clear();

		// then
		SearchWord searchWord = searchWordRepository.findByWord(word);
		assertAll(
			() -> assertThat(searchWord.getWord()).isEqualTo(word),
			() -> assertThat(searchWord.getCount()).isEqualTo(11L)
		);
	}

	@Test
	@DisplayName("인기 검색어 목록 조회시 검색 count가 높은 순서대로 최대 10개가 조회 된다.")
	void 인기_검색어_목록1() {
		// given
		// test-data.sql

		// when
		List<SearchWord> popularSearchTerms = searchWordService.findPopularSearchTerms();

		// then
		assertAll(
			() -> assertThat(popularSearchTerms).hasSize(10),
			() -> assertThat(popularSearchTerms).extracting(SearchWord::getWord, SearchWord::getCount)
				.containsExactly(
					tuple("일요일", 70L),
					tuple("토요일", 60L),
					tuple("금요일", 50L),
					tuple("가을", 45L),
					tuple("목요일", 40L),
					tuple("겨울", 35L),
					tuple("수요일", 30L),
					tuple("봄", 25L),
					tuple("화요일", 20L),
					tuple("월요일", 10L))
		);
	}
}
