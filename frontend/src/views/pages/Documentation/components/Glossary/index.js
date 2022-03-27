import PropTypes from "prop-types";
import React from "react";
import {templatedoc} from "./json-doc/template-doc";

const Glosarry = ({setDoc}) => {
  return (
    <div className="w-full h-full text-left bg-blue-300 ml-2">
      <h3>
        <strong>Getting Started</strong>
      </h3>
      <h4
        className="ml-6 underline cursor-pointer"
        onClick={() => setDoc(templatedoc)}
      >
        Templates
      </h4>
    </div>
  );
};

Glosarry.propTypes = {
  setDoc: PropTypes.func.isRequired,
};

export default Glosarry;
