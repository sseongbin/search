package com.search.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.search.domain.SearchWord;
import com.search.domain.SearchWordRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchWordService {
	private final SearchWordRepository searchWordRepository;

	@Transactional
	public void increaseCount(String word) {
		SearchWord searchWord = searchWordRepository.findByWord(word);
		if (searchWord == null) {
			searchWordRepository.save(SearchWord.init(word));
			return;
		}

		searchWordRepository.increaseCountByWord(word);
	}

	@Transactional(readOnly = true)
	public List<SearchWord> findPopularSearchTerms() {
		return searchWordRepository.findTop10ByOrderByCountDesc();
	}
}
