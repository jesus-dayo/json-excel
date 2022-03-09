import PropTypes from "prop-types";
import React, { useState } from "react";
import Modal from "react-modal/lib/components/Modal";
import { IoCloseCircle } from "react-icons/io5";
import Header from "../../../../../components/Header/Header";
import Performance from "./Performance/index";
import ReportGenerator from "./ReportGenerator";
import RightActionBar from "../../../../../components/layout/RightActionBar/index";
import Button from "../../../../../components/Button/Button";
import Divider from "../../../../../components/Divider/Divider";

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
  const [showPerf, setShowPerf] = useState(false);

  return (
    <div className="w-full">
      <Modal isOpen={isOpen} onRequestClose={onClose} style={customStyles}>
        <button onClick={onClose} className="absolute top-0 right-0 text-xl">
          <IoCloseCircle />
        </button>
        <Header title={`Test Your Template - ${template.name}`} />
        <div className="w-full">
          <RightActionBar>
            <Button onClick={() => setShowPerf(!showPerf)} variant="error">{`${
              showPerf ? "<< Back to Generator" : "Show Performance >>"
            }`}</Button>
          </RightActionBar>
        </div>
        <Divider />
        {showPerf && <Performance template={template} />}
        {!showPerf && <ReportGenerator template={template} />}
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
