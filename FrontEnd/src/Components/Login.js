import React, { useState } from "react";
import "../Styles/Login.css";
import { Link } from "react-router-dom";
import Cookies from 'js-cookie'; 
import axios from "axios";

const Login = (props) => {

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  // const [cookies, setCookie] = useCookies([""]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://16.170.255.161:8000/api/doctors/login",
        null,
        {
          params: {
            email: formData.email,
            password: formData.password,
          },
        }
      );

      // Store the token in local storage
     
      Cookies.set('patientToken', response.data.token);
      // Redirect to profile page
      if(props.title==="PATIENT"){
        window.location.href = "/Health-Plus/appointment";
      }else{
        window.location.href = "/Health-Plus/legal";
      }
    } catch (error) {
      window.alert('Invalid email or password');
      // Handle login failure
    }
  };

  // const handleSubmit = (e) => {
  //   e.preventDefault();
  //   console.log(formData);
  //   // Reset form fields after submission
  //   setFormData({
  //     email: '',
  //     password: '',
  //   });
  // };

  return (
    <div className="signup-a-container-login">
      <div className="doctor-registration-form-container">
        <h2>LOGIN {props.title}</h2>
        <br />
        <form onSubmit={handleSubmit}>
          <div className="input-group-login">
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
          <div className="input-group-login">
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

          <div className="btn">
            <button type="submit">Submit</button>
          </div>
        </form>
      </div>
      <Link to="/register" className="backBtn">
        BACK
      </Link>
    </div>
  );
};

export default Login;
