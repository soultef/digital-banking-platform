import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './pages/Header';
import LoginPage from './pages/LoginPage';
import './styles/Header.css';
import './styles/Registration.css';
import SignupPage from './pages/SignupPage';

function App() {
  return (
    <Router>

      <Header />
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignupPage />} />
        <Route path="/" element={<div style={{ padding: '20px' }}><h1>Welcome to Digital Banking Platform</h1>
        <p>
          Use the login and registration buttons above. The modals will switch based on what you click.
        </p></div>} />
      </Routes>
    </Router>
  );
}

export default App;
