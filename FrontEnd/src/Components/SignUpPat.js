import React, { useState } from 'react';
import "../Styles/SignUpPat.css";
import { Link } from 'react-router-dom';
import axios from 'axios'; 
import Cookies from 'js-cookie'; 

const SignUpDoc = () => {
  const [formData, setFormData] = useState({
    fullName: '',
    gender: '',
    age: '',
    email: '',
    password: ''
  });

  // const navigate = useNavigate(); // Initialize useNavigate hook

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://16.170.255.161:8000/api/patients/register', null, {
        params: {
          fullName: formData.fullName,
          gender: formData.gender,
          age: formData.age,
          email: formData.email,
          password: formData.password,
          
        }
      });

      Cookies.set('patientToken', response.data.token);
      window.location.href="/Health-Plus/appointment";

    } catch (error) {
      if (error.response && error.response.status === 409) {
        window.alert('Email already in use');
      } else {
        window.alert('Invalid email or password');
      }
    }
  };
  return (
    <div className='signup-a-container'>
      <div className="doctor-registration-form-container">
      <h2>PATIENT SIGNUP</h2>
      <br />
      <form onSubmit={handleSubmit}>
        <div className='input-group'>
        <label htmlFor="fullName">Full Name</label><br />
        <input
          type="text"
          id="fullName"
          name="fullName"
          value={formData.fullName}
          onChange={handleChange}
          placeholder="Full Name"
          required
        />
        </div>
        <div className='input-group'>
        <label htmlFor="gender">Gender</label>
        <input
          type="text"
          id="gender"
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          placeholder="Gender"
          required
        />
        </div>
        <div className='input-group'>
        <label htmlFor="age">Age</label>
        <input
          type="text"
          id="age"
          name="age"
          value={formData.age}
          onChange={handleChange}
          placeholder="Age"
          required
        />
        </div>
        <div className='input-group'>
        <label htmlFor="dateOfBirth">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          placeholder="Email"
          required
        />
        </div>
        <div className='input-group'>
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          placeholder="Password"
          required
        />
        </div>
        {/* <div className='input-group'>
        <label htmlFor="confirmPassword">Confirm Password</label>
        <input
          type="password"
          name="confirmPassword"
          id="confirmPassword"
          value={formData.confirmPassword}
          onChange={handleChange}
          placeholder="Confirm Password"
          required
        />
        </div> */}
        <div className='input-group full-width'></div>

        <div className="btn">
        <button type="submit">Submit</button>
        </div>
      </form>
    </div>
    <Link to="/register" className="backBtn">BACK</Link>
    </div>
  );
};

export default SignUpDoc;