import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './pages/Header';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import './styles/Header.css';
import './styles/Registration.css';

function App() {
  return (
    <Router>

      <Header />
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/" element={<div style={{ padding: '20px' }}><h1>Welcome to Digital Banking Platform</h1>
        <p>
          Use the login and registration buttons above. The modals will switch based on what you click.
        </p></div>} />
      </Routes>
    </Router>
  );
}

export default App;
