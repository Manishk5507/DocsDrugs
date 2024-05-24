import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./Pages/Home";
import Legal from "./Pages/Legal";
import NotFound from "./Pages/NotFound";
import Appointment from "./Pages/Appointment";
import LoginAsPat from "./Pages/LoginAsPatient";
import LoginAsDoc from "./Pages/LoginAsDoc";
import RegisterPage from "./Pages/RegisterPage";
import SignUpAsDoc from "./Pages/SignUpAsDoc";
import SignUpAsPat from "./Pages/SignUpAsPat";

function App() {
  return (
    <div className="App">
      <Router basename="/Health-Plus">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/legal" element={<Legal />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/register/patientlogin" element={<LoginAsPat />} />
          <Route path="/register/patientsignup" element={<SignUpAsPat />} />
          <Route path="/register/doctorsignup" element={<SignUpAsDoc />} />
          <Route path="/register/doctorlogin" element={<LoginAsDoc />} />
          <Route path="/appointment" element={<Appointment />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
