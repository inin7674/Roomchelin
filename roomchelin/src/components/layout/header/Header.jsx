import React from 'react';
import '../../../assets/css/header.css';
import { FaDoorOpen } from "react-icons/fa";

const Header = () => {
    return(
        <header className='header'>
            <h1 className='header-logo'>
                <a href="#">
                    <span>Roomchelin</span>
                    <FaDoorOpen style={{ color: '#7b62d2', cursor: 'pointer' }}/>
                </a>
            </h1>
        </header>
    )
}

export default Header;