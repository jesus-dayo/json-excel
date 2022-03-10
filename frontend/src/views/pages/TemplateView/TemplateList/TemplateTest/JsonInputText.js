import PropTypes from "prop-types";
import React, { useState } from "react";
import JSONInput from "react-json-editor-ajrm";
import { perfList } from "../../../../../services/service";

const JsonInputText = ({ setHasError, setJson, template }) => {
  const [jsonObj, setJsonObj] = useState({ test: "excel" });

  const onChange = ({ json, error }) => {
    if (error) {
      setHasError(true);
    }
    setJsonObj(JSON.parse(json));
    setJson(json);
  };

  const handleUseLast = async () => {
    const list = await perfList(template.name);
    if (!list || list.length === 0) {
      alert("No last sample data used.");
      return;
    }
    setJsonObj(JSON.parse(list[0].data));
    setJson(list[0].data);
  };

  return (
    <div className="p-2">
      <span
        className="text-lg cursor-pointer underline text-blue-500"
        onClick={handleUseLast}
      >
        use last sample
      </span>
      <JSONInput
        id={template.name}
        placeholder={jsonObj}
        height={"550px"}
        width={"700px"}
        onChange={onChange}
      />
    </div>
  );
};

JsonInputText.propTypes = {
  setHasError: PropTypes.func,
  setJson: PropTypes.func,
  template: PropTypes.shape({
    name: PropTypes.string,
  }),
};

export default JsonInputText;
