import React from 'react';
import styled from 'styled-components';

const Header = () => {
    return(
        <HeaderWrapper>
            <HeaderStyle as="div" className="header-inner">
                <HeaderLogo as="h1" className="header-logo">
                    <a to="/">
                        <span>방슐랭</span>
                    </a>
                </HeaderLogo>
            </HeaderStyle>
        </HeaderWrapper>
    )
}

const HeaderStyle = styled.header`
    div {
        display: flex;
        width: 250px;
        height: 90px;
    }
    
`;

const HeaderLogo = styled.header`
    #header-logo {
        display: block;
        transition: color 0.3s ease-in-out;
        text-transform: uppercase;
        text-align: center;
        font-size: 26px;
        color: var(--primary-dark);
        span {
            font-weight: 700;
        }
        &:hover {
            color: var(--primary);
        }
    }
`

const HeaderWrapper = styled.header`
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    width: 100%;
    padding: 0 40px;
    height: 120px;
    box-sizing: border-box;
    background-color: var(--white);
`;

export default Header;