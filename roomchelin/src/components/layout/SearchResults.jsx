import React, { useState } from "react";
import styled from "styled-components";
import Search from "./Search";
import Category from "./Category";

// 샘플 데이터
const sampleData = [
  { id: 1, name: "방탈출 홍대", location: "서울 홍대", url: "https://example.com/hongdae", foodtype: '한식' },
  { id: 2, name: "방탈출 강남", location: "서울 강남", url: "https://example.com/gangnam", foodtype: '일식'  },
  { id: 3, name: "방탈출 신촌", location: "서울 신촌", url: "https://example.com/sinchon", foodtype: '중식'  },
  { id: 4, name: "방탈출 부산", location: "부산 서면", url: "https://example.com/busan", foodtype: '양식'  },
  { id: 5, name: "방탈출 제주도", location: "제주도 제주시", url: "https://example.com/jeju", foodtype: '한식'  },
];

const SearchResults = () => {
  const [query, setQuery] = useState(""); // 검색어 상태
  const [filteredResults, setFilteredResults] = useState(sampleData); // 필터링된 결과 상태

  // 검색어 변경 시 필터링 처리
  const handleSearch = () => {
    const filtered = sampleData.filter((item) =>
      item.name.toLowerCase().includes(query.toLowerCase()) || // name에 포함된 데이터 찾기
      item.location.toLowerCase().includes(query.toLowerCase()) // location에 포함된 데이터 찾기
    );
    setFilteredResults(filtered); // 필터링된 결과 상태 업데이트
  };

  return (
    <Container>
      <Search query={query} onChange={setQuery} onSearch={handleSearch} /> {/* Search 컴포넌트에 검색어와 함수 전달 */}
      <Category/>
      <Results>
        {filteredResults.length > 0 ? (
          filteredResults.map((result) => <ResultCard key={result.id} data={result} />)
        ) : (
          <NoResults>검색 결과가 없습니다.</NoResults>
        )}
      </Results>
    </Container>
  );
};

// 검색 결과 카드 컴포넌트
const ResultCard = ({ data }) => (
  <Card>
    <a href={data.url} target="_blank" rel="noopener noreferrer">
      <h3>{data.name}</h3>
      <p>{data.location}</p>
      <p>{data.foodtype}</p>
    </a>
  </Card>
);

// 스타일링
const Container = styled.div`
  width: 100%;
  max-width: 780px;
  margin: 0 auto;
`;

const Results = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 10px;
`;

const Card = styled.div`
  border: 1px solid #eee;
  border-radius: 15px;
  background: #f9f9f9;

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
    width: 25%;
  }

  p {
    font-size: 16px;
    color: #555;
    width: 25%;
  }

  &:hover {
    background: #f1f1f1;
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
