import PropTypes from "prop-types";
import React, { useState } from "react";
import Modal from "react-modal/lib/components/Modal";
import { IoCloseCircle } from "react-icons/io5";
import Header from "../../../../../components/Header/Header";
import JSONInput from "react-json-editor-ajrm";
import Button from "../../../../../components/Button/Button";
import { generateReport } from "../../../../../services/service";
import download from "downloadjs";

const defaultCustomStyles = {
  content: {
    top: "50%",
    left: "50%",
    right: "auto",
    bottom: "auto",
    marginRight: "-50%",
    transform: "translate(-50%, -50%)",
    width: "800px",
  },
};

const TemplateTestModal = ({
  template,
  isOpen,
  customStyles = defaultCustomStyles,
  onClose,
}) => {
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
    <div className="w-full">
      <Modal isOpen={isOpen} onRequestClose={onClose} style={customStyles}>
        <button onClick={onClose} className="absolute top-0 right-0 text-xl">
          <IoCloseCircle />
        </button>
        <Header title={"Test Your Template"} />
        <div className="p-2">
          <div className="p-2">
            <h2 className="font-bold">{template.name}</h2>
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
        <div className="p-2">
          <Button
            variant="tertiary"
            onClick={() => handleGenerate(template.name)}
            disabled={hasError}
          >
            Generate Report
          </Button>
        </div>
      </Modal>
    </div>
  );
};

TemplateTestModal.propTypes = {
  customStyles: PropTypes.object,
  isOpen: PropTypes.bool,
  onClose: PropTypes.func,
  template: PropTypes.object,
};

export default TemplateTestModal;
