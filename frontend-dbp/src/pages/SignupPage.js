import React, { useState } from "react";
import "../styles/SignupPage.css";

function SignupPage() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    confirmPassword: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // TODO: Call backend API for signup
    console.log("Form submitted:", formData);
  };

  return (
    <div className="signup-container">
      <div className="signup-left">
        <div className="bank-logo">
          <h1>Digital Bank</h1>
          {/* You can replace this with an <img src="logo.png" /> */}
        </div>
      </div>

      <div className="signup-right">
        <h2>Create Your Account</h2>
        <form onSubmit={handleSubmit} className="signup-form">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            placeholder="Enter email address"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            placeholder="Enter password"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <label htmlFor="confirmPassword">Confirm Password</label>
          <input
            type="password"
            id="confirmPassword"
            name="confirmPassword"
            placeholder="Confirm password"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />

          <button type="submit" className="signup-btn">
            Sign Up
          </button>
        </form>

        <div className="login-link">
          <span>Already have an account? </span>
          <a href="/login">Login</a>
        </div>
      </div>
    </div>
  );
}

export default SignupPage;
