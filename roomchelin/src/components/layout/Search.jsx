import React, { useState, useEffect } from "react";
import styled from "styled-components";
import SearchIcon from "../../assets/img/search_icon.png";

const Search = () => {
    const [query, setQuery] = useState(""); // 검색어 상태
    const [isFocus, setIsFocus] = useState(false); // 검색박스 포커스 상태
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

    // 검색어 변경 시 호출
    const handleInputChange = (e) => {
        const input = e.target.value;
        setQuery(input);

        // 검색어 필터링 (입력 값 포함하는 연관 검색어만 표시)
        const filteredSuggestions = sampleData.filter((item) =>
        item.toLowerCase().includes(input.toLowerCase())
        );
        setSuggestions(filteredSuggestions);
    };

    // 검색창 외부 클릭 감지
    const handleClickOutside = (e) => {
        const isInsideForm = e.target.closest("form")?.dataset.id;
        setIsFocus(!!isInsideForm);
    };

    useEffect(() => {
        window.addEventListener("click", handleClickOutside);
        return () => {
        window.removeEventListener("click", handleClickOutside);
        };
    }, []);

    return (
        <SearchBox className="search-container">
        <form data-id="search-form">
            <input
            type="text"
            name="search"
            id="search"
            placeholder="방탈출 매장명 또는 지역 검색"
            value={query} // 검색어 바인딩
            onChange={handleInputChange} // 입력값 변경 이벤트
            />
            <button type="submit">Search</button>
        </form>
        {/* 연관 검색어 드롭다운 */}
        {isFocus && suggestions.length > 0 && (
            <Dropdown>
            {suggestions.map((suggestion, index) => (
                <DropdownItem
                key={index}
                onClick={() => {
                    setQuery(suggestion); // 선택한 검색어 입력창에 설정
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
