import PropTypes from "prop-types";
import React from "react";

const Header = ({ title, ...others }) => {
  return (
    <div
      className="font-sans text-lg text-white bg-blue-400 p-2 flex"
      {...others}
    >
      <h2>{title}</h2>
    </div>
  );
};

Header.propTypes = {
  title: PropTypes.string,
};

export default Header;
