import React from "react";
import styled from 'styled-components';

const Search = () => {
    return (
        <SearchBox className='search-box'>
            <form>
                <input type="text" name="search" id="search" />
                <button type="submit"></button>
            </form>
        </SearchBox>
    )
}

//style
const SearchBox = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    height: 60px;
    form {
        position: relative;
        max-width: 780px;
        width: 100%;
        display: flex;
        align-items: center;
        border-radius: 50px;
        border: 2px solid #ddd;
    }
    input {
        width: calc(100% - 60px);
        height: 60px;
        margin: 0 auto;
        padding: 25px;
        box-sizing: border-box;
        font-size: 24px;
        border: none;
        background-color: transparent;
    }
    input:focus {
        outline: none;
    }
    button {
        position: absolute;
        top: 10px;
        right: 10px;
        width: 40px;
        height: 40px;
        background-color: #000;
        border-radius: 50%;
        cursor: pointer;
    }
`
export default Search;