라이브러리
---
- lombok: getter, constructor 등 자동생성
- spring-cloud-starter-openfeign: api 호출용 client
- spring-cloud-starter-circuitbreaker-resilience4j: api 호출 실패시 fallback 구현

---
API 명세
---

- 검색 api 
  - GET: /blog/search
  - parameter
    - query(string): 검색어 필수값
    - sort(string): accuracy|recency 정렬 기준, 기본값 accuracy 
    - page(integer): 조회할 페이지, 1~50, 기본값 1
    - size(integer): 페이지 크기, 1~50, 기본값 10
  - response
    - meta
      - total_count(integer): 검색된 문서 수
      - pageable_count(integer): total_count 중 노출 가능 문서 수
      - is_end(boolean): 현재 페이지가 마지막 페이지인지 여부
    - documents
      - title(string): 블로그 글 제목
      - contents(string): 블로그 글 요약
      - url(string): 블로그 글 URL
      - blogname(string): 블로그의 이름
      - thumbnail(string): 검색 시스템에서 추출한 대표 미리보기 이미지 URL
      - datetime(datetime):	블로그 글 작성시간, ISO 8601
        [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
---
- 인기 검색어 조회 api
  - 인기가 많은 검색어를 최대 10개까지 보여준다. 
  - GET: /blog/search/popular-search-words
  - response:
    - word(string): 검색어
    - count(long): 검색 횟수

---
기능 요구 사항
---

1. 블로그 검색
   - [x] 키워드를 통한 검색
   - [x] 정확도순, 최신순 정렬
   - [x] pagination
   - [x] 카카오 API 연동
     - [x] 타 서비스 변경 가능
2. 인기 검색어 목록
   - [x] 사용자들이 검색한 순서대로 최대 10개의 검색 키워드 제공
   - [x] 검색어별 검색 횟수 함께 제공

---
Todo

- [x] 검색 조회 api
  - [x] 조회 api 구현 
  - [x] 예외 처리
  - [x] 테스트 코드
  - [x] 카카오 API 에러시 네이버 API 호출

- [x] 인기 검색어 조회 api
  - [x] 검색시 검색 횟수 저장
  - [x] 인기 검색어 조회 api 구현
  - [x] 테스트 코드

- [x] executable jar 생성
- [x] 사용한 라이브러리 정리
- [x] api 명세
- [x] 모듈 분리
