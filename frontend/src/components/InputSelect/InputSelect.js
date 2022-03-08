import PropTypes from "prop-types";
import React from "react";

const InputSelect = ({
  onChange,
  value,
  options = [],
  label,
  placeholder,
  id,
  ...others
}) => {
  return (
    <div className="flex w-full align-baseline mb-6 md:mb-0">
      {label && (
        <label
          className="block tracking-wide text-gray-700 text-lg m-2 text-right"
          htmlFor={id}
        >
          {label}
        </label>
      )}
      <select
        className="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        id={id}
        {...others}
      >
        {options.map((option, index) => (
          <option key={`${option.value}-${index}`} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
    </div>
  );
};

InputSelect.propTypes = {
  id: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  label: PropTypes.string,
  onChange: PropTypes.func,
  options: PropTypes.array,
  placeholder: PropTypes.string,
  value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
};

export default InputSelect;
