import PropTypes from "prop-types";
import React from "react";

const Container = ({ children }) => {
  return <div className="w-full h-full">{children}</div>;
};

Container.propTypes = {
  children: PropTypes.element,
};

export default Container;
