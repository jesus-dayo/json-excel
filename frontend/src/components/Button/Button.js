import PropTypes from "prop-types";
import React from "react";

const colors = {
  primary: "bg-black text-white hover:bg-gray-600",
  secondary: "bg-blue-600 text-white hover:bg-blue-400",
  tertiary: "bg-yellow-600 text-white hover:bg-yellow-400",
  error: "bg-red-600 text-white hover:bg-red-400",
};

const sizes = {
  xs: "h-10 w-28",
  s: "h-12 w-32",
};

const Button = ({
  variant = "primary",
  size = "s",
  children,
  customStyle = "",
  disabled = false,
  className,
  onClick = () => {},
}) => {
  return (
    <>
      {disabled ? (
        <button
          onClick={onClick}
          className={
            `bg-gray-500 text-white cursor-not-allowed ${sizes[size]} ` +
            className
          }
          disabled={disabled}
        >
          {children}
        </button>
      ) : (
        <button
          onClick={onClick}
          className={
            `${colors[variant]} ${sizes[size]} ${customStyle}` + className
          }
          disabled={disabled}
        >
          {children}
        </button>
      )}
    </>
  );
};

Button.propTypes = {
  children: PropTypes.element,
  customStyle: PropTypes.string,
  onClick: PropTypes.func,
  size: PropTypes.string,
  variant: PropTypes.oneOf(["primary", "secondary", "tertiary"]),
  disabled: PropTypes.bool,
  className: PropTypes.object,
};

export default Button;
