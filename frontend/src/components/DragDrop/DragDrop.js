import PropTypes from "prop-types";
import React from "react";
import { FileUploader } from "react-drag-drop-files";

const fileTypes = ["XLS", "XLSX"];

const DragDrop = ({ onDrop }) => {
  const handleChange = (file) => {
    onDrop(file);
  };

  return (
    <FileUploader
      classes="h-full"
      handleChange={handleChange}
      name="file"
      types={fileTypes}
    />
  );
};

DragDrop.propTypes = {
  onDrop: PropTypes.func,
};

export default DragDrop;
