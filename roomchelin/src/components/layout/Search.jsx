import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { IoSearch } from "react-icons/io5";

const Search = ({ query, onChange, onSearch }) => {
    const [isFocus, setIsFocus] = useState(false);
    const [suggestions, setSuggestions] = useState([]);
    const [filteredSuggestions, setFilteredSuggestions] = useState([]);
    const [isResultsVisible, setIsResultsVisible] = useState(false);  // 결과 표시 여부 상태

    const fetchSuggestions = async () => {
        try {
            const response = await fetch(`http://13.124.237.2:8080/findall`);
            if (!response.ok) {
                throw new Error("Failed to fetch suggestions");
            }
            const data = await response.json();
            if (Array.isArray(data)) {
                setSuggestions(data);
            } else if (data && data.suggestions) {
                setSuggestions(data.suggestions);
            }
        } catch (error) {
            console.error("Error fetching suggestions:", error);
            setSuggestions([]);
        }
    };

    const filterSuggestions = (input) => {
        const filtered = suggestions.filter((suggestion) =>
            suggestion.escape_store.toLowerCase().includes(input.toLowerCase())
        );
        setFilteredSuggestions(filtered);
    };

    const handleInputChange = (e) => {
        const input = e.target.value;
        onChange(input);

        // 검색어가 입력될 때마다 필터링 적용
        filterSuggestions(input);
    };

    const handleClickOutside = (e) => {
        if (!e.target.closest("form")) {
            setIsFocus(false);
        }
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        // 검색 버튼 클릭 시에만 결과를 표시
        setIsResultsVisible(true);
        if (onSearch) {
            onSearch();
        }
        setIsFocus(false);
    };

    useEffect(() => {
        // 컴포넌트가 처음 렌더링될 때 데이터 가져오기
        fetchSuggestions();

        // 외부 클릭 감지
        window.addEventListener("click", handleClickOutside);
        return () => {
            window.removeEventListener("click", handleClickOutside);
        };
    }, []);

    // 검색어가 비어있을 때 드롭다운을 표시하지 않도록 조건 추가
    const shouldShowDropdown = query.trim() !== "" && (isFocus || !isResultsVisible);

    return (
        <SearchBox className="search-container">
            <form onSubmit={handleSearchSubmit}>
                <input
                    type="text"
                    name="search"
                    id="search"
                    placeholder="방탈출 매장명 또는 지역 검색"
                    value={query}
                    onChange={handleInputChange}
                    onFocus={() => setIsFocus(true)}
                />
                <button type="submit">
                    <IoSearch style={{ color: "#7b62d2", fontSize: "30px", cursor: "pointer" }} />
                </button>
            </form>

            {/* 검색어가 입력되고, 필터링된 결과를 실시간으로 보여주는 드롭다운 */}
            {shouldShowDropdown && suggestions.length > 0 && !isResultsVisible && (
                <Dropdown>
                    {filteredSuggestions.length > 0
                        ? filteredSuggestions.map((suggestion, index) => (
                            <DropdownItem
                                key={index}
                                onClick={() => {
                                    onChange(suggestion.escape_store);
                                    setIsFocus(false);
                                }}
                            >
                                {suggestion.escape_store}
                            </DropdownItem>
                        ))
                        : <DropdownItem>결과 없음</DropdownItem>
                    }
                </Dropdown>
            )}

            {/* 검색 버튼 클릭 시 필터링된 결과만 드롭다운에 표시 */}
            {isResultsVisible && filteredSuggestions.length > 0 && (
                <Dropdown>
                    {filteredSuggestions.map((suggestion, index) => (
                        <DropdownItem
                            key={index}
                            onClick={() => {
                                onChange(suggestion.escape_store);
                                setIsResultsVisible(false);
                            }}
                        >
                            {suggestion.escape_store}
                        </DropdownItem>
                    ))}
                </Dropdown>
            )}
        </SearchBox>
    );
};

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
        border-radius: 50%;
        background-color: #fff;
        border: none;
        padding: 0%;
        font-size: 0;
        cursor: pointer;
    }
`;

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
