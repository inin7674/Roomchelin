import React from "react";
import styled from "styled-components";

const Category = () => {
    return (
        <CategoryContainer className="category-container">
            <p>매장명</p>
            <p>지역</p>
            <p>종류</p>
        </CategoryContainer>
    )
}

const CategoryContainer = styled.div`
    max-width: 780px;
    width: 100%;
    margin: 30px auto 0;
    background-color: #7b62d2;
    height: 60px;
    border-radius: 15px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #fff;
    padding: 0 20px;
    box-sizing: border-box;
    p {
        text-align: center;
        font-size: 19px;
        width: 25%;
    }
`

export default Category;