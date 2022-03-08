import PropTypes from "prop-types";
import React from "react";
import { Link } from "react-router-dom";

const LinkButton = ({ route, onClick, active, children, ...props }) => {
  return (
    <div
      className={`p-4 m-2 ${
        active
          ? "bg-blue-600 cursor-default"
          : "bg-blue-400 hover:bg-blue-200 cursor-pointer"
      } w-40 h-16 flex`}
    >
      <Link
        onClick={onClick}
        to={route}
        {...props}
        className={`no-underline text-white ${
          active ? "cursor-default" : "cursor-pointer"
        }`}
      >
        {children}
      </Link>
    </div>
  );
};

LinkButton.propTypes = {
  route: PropTypes.string,
  onClick: PropTypes.func,
  active: PropTypes.bool,
  children: PropTypes.string.isRequired,
};

export default LinkButton;
