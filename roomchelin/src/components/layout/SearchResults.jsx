import React, { useState, useEffect } from "react";
import styled from "styled-components";
import Search from "./Search";
import Category from "./Category";

const SearchResults = ({ data = [] }) => {
  const [query, setQuery] = useState(""); // 검색어 상태
  const [filteredResults, setFilteredResults] = useState(data); // 필터링된 결과

  // 검색어 변경 시 필터링 처리
  const handleSearch = () => {
    console.log("Query:", query); // query 값 확인

    const filtered = data.filter(
      (item) =>
        (item.escape_store && item.escape_store.includes(query)) ||
        (item.region_sub && item.region_sub.includes(query)) ||
        (item.telephone && item.telephone.includes(query))
    );
    setFilteredResults(filtered); // 필터링된 결과 업데이트
  };

  // 검색어 변경 시 자동으로 필터링
  useEffect(() => {
    handleSearch();
  }, [query, data]); // query와 data가 변경될 때마다 handleSearch 실행

  return (
    <Container>
      <Search query={query} onChange={setQuery} onSearch={handleSearch} />
      <Results>
        {filteredResults.length > 0 ? (
          filteredResults.map((result, index) => (
            <ResultCard key={index} data={result} />
          ))
        ) : (
          <NoResults>검색 결과가 없습니다.</NoResults>
        )}
      </Results>
    </Container>
  );
};

// 검색 결과 카드 컴포넌트
const ResultCard = ({ data }) => {
  return (
    <Card>
      <a href="#" target="_blank" rel="noopener noreferrer">
        {/* 객체의 각 속성을 개별적으로 렌더링 */}
        <h3>{data.escape_store}</h3>
        <p>{data.region_sub}</p>
        <p>{data.road_address || "주소 없음"}</p> {/* 주소가 없을 때 기본값 */}
      </a>
    </Card>
  );
};

// 스타일링
const Container = styled.div`
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
  @media (max-width: 820px) {
    width: 86.6666vw;
    margin: 0 auto;
  }
`;

const Results = styled.div`
  display: flex;
  flex-direction: column;
`;

const Card = styled.div`
  border-bottom: 1px solid rgba(123, 98, 210, 0.2);
  background: #fff;

  a {
    padding: 20px;
    text-decoration: none;
    color: inherit;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
  }

  h3 {
    margin: 0;
    font-size: 19px;
    width: 40%;
    padding-left: 5%;
    box-sizing: border-box;
    text-align: left;
    color: #333;
  }

  p {
    font-size: 16px;
    color: #555;
    width: 20%;
  }

  &:hover {
    background: #fbfbfb;
    cursor: pointer;
  }
`;

const NoResults = styled.div`
  text-align: center;
  color: #999;
  font-size: 16px;
  padding: 70px;
  box-sizing: border-box;
`;

export default SearchResults;
