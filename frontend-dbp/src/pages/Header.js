import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Header.css';

function Header() {
  return (
    <header className='header'>
      <div className='logo'>MyCompany</div>

      <div className='search-bar'>
        <input type='text' placeholder='Search...' />
      </div>

      <div className='header-buttons'>
        <Link to="/login">
          <button className='btn login-btn'>Login</button>
        </Link>

        <Link to="/signup">
          <button className='btn register-btn'>Signup</button>
        </Link>
      </div>
    </header>
  );
}

export default Header;
