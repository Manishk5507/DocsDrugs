import React from "react";
import "../Styles/Register.css";
import { Link } from "react-router-dom";

const Register = () => {
  return (
    <div className="register-a-container">
      <div className="boxes-boxes" >
        <div className="childbox">
          <h1>PATIENT</h1>

          <Link to="/register/patientlogin" className="login">
            LOGIN ⏩
          </Link>
          <Link to="/register/patientsignup" className="signup">
            SIGNUP ⏩
          </Link>
        </div>
        <div className="childbox">
          <h1>DOCTOR</h1>
          <Link to="/register/doctorlogin" className="login">
            LOGIN ⏩
          </Link>
          <Link to="/register/doctorsignup" className="signup">
            SIGNUP ⏩
          </Link>

        </div>
        <Link to="/" className="backBtn">BACK</Link>
      </div>
    </div>
  );
};

export default Register;
