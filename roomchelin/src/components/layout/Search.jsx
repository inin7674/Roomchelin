import React, { useState, useEffect } from "react";
import styled from "styled-components";
import SearchIcon from "../../assets/img/search_icon.png";

const Search = ({ query, onChange, onSearch }) => {
    const [isFocus, setIsFocus] = useState(false); // 검색창 포커스 상태
    const [suggestions, setSuggestions] = useState([]); // 연관 검색어 상태

  // 샘플 데이터 (연관 검색어)
    const sampleData = [
        "방탈출 홍대",
        "방탈출 강남",
        "방탈출 신촌",
        "방탈출 부산",
        "방탈출 제주도",
        "방탈출 매장 추천",
    ];

  // 검색어 변경 시 연관 검색어 필터링
    const handleInputChange = (e) => {
        const input = e.target.value;
        onChange(input); // 부모 컴포넌트에 검색어 전달

        // 검색어 필터링 (입력 값 포함하는 연관 검색어만 표시)
        const filteredSuggestions = sampleData.filter((item) =>
        item.toLowerCase().includes(input.toLowerCase())
        );
        setSuggestions(filteredSuggestions);
    };

    // 검색창 외부 클릭 감지
    const handleClickOutside = (e) => {
        if (!e.target.closest("form")) {
        setIsFocus(false); // 외부 클릭 시 드롭다운 닫기
        }
    };

    useEffect(() => {
        window.addEventListener("click", handleClickOutside);
        return () => {
        window.removeEventListener("click", handleClickOutside);
        };
    }, []);

    // 검색버튼 클릭 또는 엔터키 입력 시 드롭다운 닫기
    const handleSearchSubmit = (e) => {
        e.preventDefault(); // 기본 폼 제출 동작 방지
        onSearch(); // 부모 컴포넌트의 검색 실행 함수 호출
        setIsFocus(false); // 드롭다운 닫기
    };

    return (
        <SearchBox className="search-container">
            <form onSubmit={handleSearchSubmit}>
                <input
                type="text"
                name="search"
                id="search"
                placeholder="방탈출 매장명 또는 지역 검색"
                value={query} // 검색어 바인딩
                onChange={handleInputChange} // 입력값 변경 이벤트
                onFocus={() => setIsFocus(true)} // 포커스 시 드롭다운 표시
                />
                <button type="submit">
                <img src={SearchIcon} alt="search" />
                </button>
            </form>
            
            {/* 드롭다운 */}
            {isFocus && suggestions.length > 0 && (
                <Dropdown>
                {suggestions.map((suggestion, index) => (
                    <DropdownItem
                    key={index}
                    onClick={() => {
                        onChange(suggestion); // 선택한 검색어 입력창에 설정
                        setIsFocus(false); // 드롭다운 닫기
                    }}
                    >
                    {suggestion}
                    </DropdownItem>
                ))}
                </Dropdown>
            )}
        </SearchBox>
    );
};

// 스타일
const SearchBox = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    height: 60px;
    position: relative;

    form {
        position: relative;
        max-width: 780px;
        width: 100%;
        display: flex;
        align-items: center;
        border-radius: 50px;
        border: 2px solid #7b62d2;
    }

    input {
        width: calc(100% - 60px);
        height: 60px;
        margin: 0 auto;
        box-sizing: border-box;
        font-size: 19px;
        border: none;
        background-color: transparent;
    }

    input:focus {
        outline: none;
    }

    input::placeholder {
        font-size: 16px;
        color: #ddd;
        transition: color 0.1s;
    }

    input:focus::placeholder {
        color: transparent;
    }

    button {
        position: absolute;
        top: 10px;
        right: 10px;
        width: 40px;
        height: 40px;
        background-image: url(${SearchIcon});
        background-position: center;
        background-size: 30px 30px;
        background-repeat: no-repeat;
        border-radius: 50%;
        background-color: #fff;
        border: none;
        padding: 0%;
        font-size: 0;
        cursor: pointer;
    }
`;

// 드롭다운 스타일
const Dropdown = styled.ul`
    position: absolute;
    top: 60px;
    left: 50%;
    margin-left: -390px;
    width: 780px;
    background: #fff;
    border: 1px solid #ccc;
    border-radius: 4px;
    list-style: none;
    padding: 10px 0;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    z-index: 10;
`;

const DropdownItem = styled.li`
    padding: 8px 16px;
    cursor: pointer;
    transition: background 0.2s;

    &:hover {
        background: #f0f0f0;
    }
`;

export default Search;
