import React from "react";
import logo from "../../../assets/logo.png";

const NavBar = ({ ...props }) => {
  return (
    <div className="w-full flex bg-blue-500 text-white h-20" {...props}>
      <img src={logo} className="m-3" />
      <div className="m-auto">Template Report Maintenance</div>
    </div>
  );
};

export default NavBar;
