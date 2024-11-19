import React from "react";
import Header from "./header/Header";
import Search from "./Search";
import SearchResults from "./SearchResults";
import styled from "styled-components";

const Layout = ({ title, pagename, children }) => {
  // const { pagename, children } = props;
    return (
        <Wrap>
            <Header />
            <main id="main">
                <SearchResults/>
            </main>
        </Wrap>
    );
};

const Wrap = styled.div`
    display: flex;
    flex-direction: column;
    background-color: #fff;
    gap: 60px;
`;

export default Layout;