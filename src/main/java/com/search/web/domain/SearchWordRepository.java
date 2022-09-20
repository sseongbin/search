package com.search.web.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SearchWordRepository extends JpaRepository<SearchWord, Long> {

	SearchWord findByWord(String word);

	@Modifying
	@Query("UPDATE SearchWord s SET s.count = s.count + 1 WHERE s.word = :word")
	void increaseCountByWord(String word);

	List<SearchWord> findTop10ByOrderByCountDesc();
}
