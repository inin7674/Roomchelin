import React from "react";
import Header from "./header/Header";
import styled from "styled-components";

const Layout = ({ title, pagename, children }) => {
  // const { pagename, children } = props;
    return (
        <Wrap>
            <Header />
            <main id="main">

            </main>
        </Wrap>
    );
};

const Wrap = styled.div`
    display: flex;
    flex-direction: column;
    background: var(--secondary-grey-300, #f4f7fe);

`;

export default Layout;