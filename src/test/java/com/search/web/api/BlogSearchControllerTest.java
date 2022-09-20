package com.search.web.api;

import static org.hamcrest.core.StringContains.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.search.web.service.SearchWordService;

@WebMvcTest(BlogSearchController.class)
class BlogSearchControllerTest {
	@Autowired
	MockMvc mvc;

	@MockBean
	SearchResultReadService searchResultReadService;

	@MockBean
	SearchWordService searchWordService;

	@Test
	@DisplayName("검색어가 없는 경우 400 응답")
	void 검색_예외_테스트1() throws Exception {
		// given, when, then
		mvc.perform(get("/blog/search"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", containsString("query:검색어는 필수 입니다.")));
	}

	@Test
	@DisplayName("page를 0으로 요청 하는 경우 400 응답")
	void 검색_예외_테스트2() throws Exception {
		// given
		int page = 0;

		// when, then
		mvc.perform(get("/blog/search")
				.queryParam("query", "검색어")
				.queryParam("page", Integer.toString(page)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", containsString("페이지는 1~50 사이의 값을 입력해야 합니다.")));
	}

	@Test
	@DisplayName("page를 51로 요청 하는 경우 400 응답")
	void 검색_예외_테스트3() throws Exception {
		// given
		int page = 51;

		// when, then
		mvc.perform(get("/blog/search")
				.queryParam("query", "검색어")
				.queryParam("page", Integer.toString(page)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", containsString("페이지는 1~50 사이의 값을 입력해야 합니다.")));
	}

	@Test
	@DisplayName("size를 0으로 요청 하는 경우 400 응답")
	void 검색_예외_테스트4() throws Exception {
		// given
		int size = 0;

		// when, then
		mvc.perform(get("/blog/search")
				.queryParam("query", "검색어")
				.queryParam("size", Integer.toString(size)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", containsString("사이즈는 1~50 사이의 값을 입력해야 합니다.")));
	}

	@Test
	@DisplayName("size를 51로 요청 하는 경우 400 응답")
	void 검색_예외_테스트5() throws Exception {
		// given
		int size = 51;

		// when, then
		mvc.perform(get("/blog/search")
				.queryParam("query", "검색어")
				.queryParam("size", Integer.toString(size)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message", containsString("사이즈는 1~50 사이의 값을 입력해야 합니다.")));
	}
}
