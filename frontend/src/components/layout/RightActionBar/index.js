import PropTypes from 'prop-types';
import React from 'react';

const RightActionBar = ({ children, ...others }) => {
  return (
    <div className="m-2 w-full">
      <div className="flex pr-4" {...others}>
        {children}
      </div>
    </div>
  );
};

RightActionBar.propTypes = {
  children: PropTypes.element,
};

export default RightActionBar;
