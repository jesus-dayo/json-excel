import PropTypes from "prop-types";
import React from "react";

const InputText = ({
  onChange,
  onKeyPress,
  value,
  label,
  placeholder,
  id,
  labelStyle,
  disabled,
  maxLength,
  ...others
}) => {
  return (
    <div className="flex w-full mb-6 md:mb-0">
      {label && (
        <label
          className={`block tracking-wide text-gray-700 text-lg m-2 text-right ${labelStyle}`}
          htmlFor={id}
        >
          {label}
        </label>
      )}
      <input
        className={`appearance-none block w-full ${
          disabled ? "bg-gray-400" : "bg-gray-200"
        } text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
        id={id}
        type="text"
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        onKeyPress={onKeyPress}
        disabled={disabled}
        maxLength={maxLength}
        {...others}
      />
    </div>
  );
};

InputText.propTypes = {
  id: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  label: PropTypes.string,
  labelStyle: PropTypes.string,
  onChange: PropTypes.func,
  placeholder: PropTypes.string,
  value: PropTypes.string,
  onKeyPress: PropTypes.func,
  disabled: PropTypes.bool,
  maxLength: PropTypes.number,
};

export default InputText;
