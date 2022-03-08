import PropTypes from 'prop-types';
import React from 'react';

const sizes = {
  s: 'text-3xl',
};

const IconButton = ({ icon, onClick, size = 's', children, ...others }) => {
  return (
    <div
      className={`text-center ${sizes[size]} hover:text-blue-400`}
      {...others}
      onClick={onClick}
    >
      {children}
      {icon}
    </div>
  );
};

IconButton.propTypes = {
  children: PropTypes.element,
  icon: PropTypes.element,
  onClick: PropTypes.func,
  size: PropTypes.string,
};

export default IconButton;
