import React, { useState, useEffect } from "react";
import { useMediaQuery } from "react-responsive";
import "./App.css";
import Layout from "./components/layout/Layout";
import SearchResults from "./components/layout/SearchResults";

function App() {
  const [data, setData] = useState([]);

  // API 호출
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://15.164.160.128:8080/roomfindall");

        // 응답이 성공적인지 확인
        if (!response.ok) {
          throw new Error(`Failed to fetch data: ${response.statusText}`);
        }

        const result = await response.json(); // 응답을 JSON 형식으로 변환
        console.log("API 응답:", result); // 데이터 구조 확인

        if (Array.isArray(result)) {
          setData(result); // 데이터가 배열이면 바로 상태에 저장
        } else {
          console.error("API 응답이 배열이 아닙니다:", result);
        }
      } catch (error) {
        console.error("Error fetching data:", error.message);
      }
    };

    fetchData();
  }, []); // 의존성 배열 비워두어 첫 렌더링 시 한 번만 호출

  return (
    <div className="App">
      <Layout />
      <SearchResults data={data} />
    </div>
  );
}

export default App;
