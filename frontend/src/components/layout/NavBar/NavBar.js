import React from "react";
import logo from "../../../assets/logo.png";
import {useNavigate} from "react-router-dom";

const NavBar = ({ ...props }) => {
  const navigate = useNavigate();
  return (
    <div className="w-full flex bg-blue-500 text-white h-20" {...props}>
      <img
        alt={"logo"}
        src={logo}
        className="m-3 cursor-pointer"
        onClick={() => navigate("/")}
      />
      <div className="m-auto cursor-pointer" onClick={() => navigate("/")}>
        Template Report Maintenance
      </div>
      <div
        className="mt-7 mr-8 text-lg text-black underline cursor-pointer"
        onClick={() => navigate("/documentation")}
      >
        Documentation
      </div>
    </div>
  );
};

export default NavBar;
