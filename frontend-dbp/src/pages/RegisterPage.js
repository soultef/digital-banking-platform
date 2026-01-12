import React, { useState } from "react";
import axios from "axios";
import "../styles/Registration.css";

const Registration = () => {
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    birthDate: "",
    addresses: [
      {
        streetNumber: "",
        streetName: "",
        unitNumber: "",
        city: "",
        state: "",
        zipcode: "",
        county: "",
        country: "",
      },
    ],
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleChange = (e, index, section) => {
    if (section === "addresses") {
      const updated = [...user.addresses];
      updated[index][e.target.name] = e.target.value;
      setUser({ ...user, addresses: updated });
    } else {
      setUser({ ...user, [e.target.name]: e.target.value });
    }
  };

  const addAddress = () => {
    setUser({
      ...user,
      addresses: [
        ...user.addresses,
        {
          streetNumber: "",
          streetName: "",
          unitNumber: "",
          city: "",
          state: "",
          zipcode: "",
          county: "",
          country: "",
        },
      ],
    });
  };

  const removeAddress = (index) => {
    setUser({
      ...user,
      addresses: user.addresses.filter((_, i) => i !== index),
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/register",
        user,
        { headers: { "Content-Type": "application/json" } }
      );

      setMessage(`✅ ${response.data.email} registered successfully`);
    } catch (error) {
      setMessage(
        error.response?.data?.message ||
          "❌ Registration failed. Please try again."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="registration-container">
      <h2>User Registration</h2>

      <form onSubmit={handleSubmit}>
        {/* USER INFO */}
        <h3>User Information</h3>

        <div className="section-block">
          <div className="row three-cols">
            <input
              placeholder="First Name *"
              name="firstName"
              value={user.firstName}
              onChange={handleChange}
              required
            />
            <input
              placeholder="Last Name *"
              name="lastName"
              value={user.lastName}
              onChange={handleChange}
              required
            />
            <input
              placeholder="Birth Date *"
              type="date"
              name="birthDate"
              value={user.birthDate}
              onChange={handleChange}
              required
            />
          </div>

          <div className="row two-cols">
            <input
              placeholder="Email *"
              type="email"
              name="email"
              value={user.email}
              onChange={handleChange}
              required
            />
            <input
              placeholder="Phone *"
              name="phone"
              value={user.phone}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        {/* ADDRESSES */}
        <h3>Addresses</h3>

        {user.addresses.map((addr, index) => (
          <div className="section-block" key={index}>
            <div className="row three-cols">
              <input
                placeholder="Street #"
                name="streetNumber"
                value={addr.streetNumber}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
              <input
                placeholder="Street Name"
                name="streetName"
                value={addr.streetName}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
              <input
                placeholder="Unit"
                name="unitNumber"
                value={addr.unitNumber}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
            </div>

            <div className="row three-cols">
              <input
                placeholder="City"
                name="city"
                value={addr.city}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
              <input
                placeholder="State"
                name="state"
                value={addr.state}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
              <input
                placeholder="Zip Code"
                name="zipcode"
                value={addr.zipcode}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
            </div>

            <div className="row two-cols">
              <input
                placeholder="County"
                name="county"
                value={addr.county}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
              <input
                placeholder="Country"
                name="country"
                value={addr.country}
                onChange={(e) => handleChange(e, index, "addresses")}
              />
            </div>

            {user.addresses.length > 1 && (
              <button
                type="button"
                className="remove-btn"
                onClick={() => removeAddress(index)}
              >
                Remove Address
              </button>
            )}
          </div>
        ))}

        <button type="button" className="add-btn" onClick={addAddress}>
          + Add Address
        </button>

        <button type="submit" className="submit-btn" disabled={loading}>
          {loading ? "Submitting..." : "Register"}
        </button>

        {message && <p className="message">{message}</p>}
      </form>
    </div>
  );
};

export default Registration;
