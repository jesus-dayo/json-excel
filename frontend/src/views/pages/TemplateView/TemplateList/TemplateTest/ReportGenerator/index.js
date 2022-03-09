import PropTypes from "prop-types";
import React, { useState } from "react";
import JSONInput from "react-json-editor-ajrm";
import Button from "../../../../../../components/Button/Button";
import { generateReport } from "../../../../../../services/service";
import download from "downloadjs";

const ReportGenerator = ({ template }) => {
  const [json, setJson] = useState();
  const [jsonObj, setJsonObj] = useState({ test: "excel" });
  const [hasError, setHasError] = useState(false);

  const onChange = ({ json, error }) => {
    if (error) {
      setHasError(true);
    }
    setJsonObj(JSON.parse(json));
    setJson(json);
  };

  const handleGenerate = async () => {
    let filename = null;
    generateReport(template.name, json)
      .then((response) => {
        if (response.status === 200) {
          filename = response.headers
            .get("content-disposition")
            .split(";")[1]
            .split("=")[1];
          return response.blob();
        } else {
          return;
        }
      })
      .then((body) => {
        download(body, filename, "application/octet-stream");
      });
  };

  return (
    <>
      <div className="p-2">
        <div className="p-2">
          <div className="p-2">
            <JSONInput
              id={template.name}
              placeholder={jsonObj}
              height={"550px"}
              width={"700px"}
              onChange={onChange}
            />
          </div>
        </div>
      </div>
      <div className="p-2 flex gap-2">
        <Button variant="tertiary" onClick={handleGenerate} disabled={hasError}>
          Generate Report
        </Button>
      </div>
    </>
  );
};

ReportGenerator.propTypes = {
  customStyles: PropTypes.object,
  isOpen: PropTypes.bool,
  onClose: PropTypes.func,
  template: PropTypes.object,
};

export default ReportGenerator;
