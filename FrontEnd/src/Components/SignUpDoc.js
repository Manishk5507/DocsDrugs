import React, { useState } from 'react';
import "../Styles/SignUpDoc.css"
import { Link } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie'; 

const SignUpDoc = () => {
  const [formData, setFormData] = useState({
    fullName: '',
    gender: '',
    email: '',
    password: '',
    medicalDegree: '',
    specialization: '',
    hospital: '',
    registrationNumber: ''
    // certifyInformation: false 
  });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://16.170.255.161:8000/api/doctors/register', null, {
        params: {
          fullName: formData.fullName,
          gender: formData.gender,
          email: formData.email,
          password: formData.password,
          medicalDegree: formData.medicalDegree,
          specialization: formData.specialization,
          hospital: formData.hospital,
          registrationNumber: formData.registrationNumber
        }
      });
      console.log(response.data); // You can log the response from the backend

      // Save token in a cookie upon successful registration

      Cookies.set('doctorToken', response.data.token);

      // Reset form fields after successful submission
      setFormData({
        fullName: '',
        gender: '',
        email: '',
        password: '',
        medicalDegree: '',
        specialization: '',
        hospital: '',
        registrationNumber: ''
        // certifyInformation: false 
      });

      // Redirect to Google after successful submission
      window.location.href = "/Health-Plus/legal";
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
      <h2>DOCTOR SIGNUP</h2>
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
          placeholder="FullName"
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
        {/* <div className='input-group'>
        <label htmlFor="dateOfBirth">Date Of Birth</label>
        <input
          type="text"
          id="dateOfBirth"
          name="dateOfBirth"
          value={formData.dateOfBirth}
          onChange={handleChange}
          placeholder="Date of Birth"
          required
        />
        </div> */}
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
        <div className="input-group">
        <label htmlFor="medicalDegree">Medical Degree</label>
        <input
          type="text"
          id="medicalDegree"
          name="medicalDegree"
          value={formData.medicalDegree}
          onChange={handleChange}
          placeholder="Medical Degree"
          required
        />
        </div>
        <div className="input-group">
        <label htmlFor="specialization">Specialization</label>
        <input
          type="text"
          id="specialization"
          name="specialization"
          value={formData.specialization}
          onChange={handleChange}
          placeholder="Specialization"
          required
        />
        </div>
        <div className="input-group">
        <label htmlFor="hospital">Hospital</label>
        <input
          type="text"
          id="hospital"
          name="hospital"
          value={formData.hospital}
          onChange={handleChange}
          placeholder="Hospital"
          required
        />
        </div>
        <div className="input-group">
        <label htmlFor="registrationNumber">Registration Number</label>
        <input
          type="text"
          id="registrationNumber"
          name="registrationNumber"
          value={formData.registrationNumber}
          onChange={handleChange}
          placeholder="Registration Number"
          required
        />
        </div>

        {/* <div className='input-group full-width'>
        <label htmlFor="certifyInformation">I hereby certify that the above information is true and accurate to the best of my knowledge.</label>
          <input
            type="checkbox"
            id="certifyInformation"
            name="certifyInformation"
            checked={formData.certifyInformation}
            onChange={handleChange}
          />
        </div> */}
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