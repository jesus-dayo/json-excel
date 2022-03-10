import PropTypes from "prop-types";
import React from "react";
import InputText from "../../../../../../components/InputText/InputText";
import DragDrop from "../../../../../../components/DragDrop/DragDrop";
import { uploadTemplate } from "../../../../../../services/service";
import Button from "../../../../../../components/Button/Button";

const TemplateForm = ({
  template,
  dispatchTemplate,
  save,
  doesNameExist,
  exist,
  isUpdate,
}) => {
  const handleUpload = async (file) => {
    const json = await uploadTemplate(
      file,
      template.name,
      template.description
    );
    dispatchTemplate({ type: "JSON", payload: json });
  };

  return (
    <div className="w-full p-4">
      <div className="mb-2 text-left">
        <text className="text-lg font-bold">Template Structure</text>
        <text> - Start by uploading your excel file template</text>
      </div>
      <div className="mb-6 w-full text-left">
        <DragDrop onDrop={handleUpload} />
      </div>
      <div className="w-full max-w-lg">
        <div className="flex flex-wrap -mx-3 mb-6">
          <InputText
            label={"*Name:"}
            id={"name"}
            onChange={(e) => {
              const val = e.target.value?.replace(" ", "");
              dispatchTemplate({
                type: "UPDATE",
                payload: {
                  ...template,
                  name: val,
                },
              });
              if (val && val.length >= 6) {
                doesNameExist(val);
              }
            }}
            value={template.name}
            maxLength={20}
            placeholder="Enter template name without spaces (min 6 , max 20 char)"
            disabled={isUpdate}
          />
          {exist && template.name?.length >= 6 && (
            <span className="flex flex-wrap m-1 text-red-400 text-sm">
              Name {template.name} already exist.
            </span>
          )}
        </div>
      </div>
      <div className="w-full max-w-lg">
        <div className="flex flex-wrap -mx-3 mb-6">
          <InputText
            label={"*Description:"}
            id={"description"}
            onChange={(e) => {
              dispatchTemplate({
                type: "UPDATE",
                payload: { ...template, description: e.target.value },
              });
            }}
            value={template.description}
            maxLength={300}
            placeholder="Enter description (max 300 char)"
          />
        </div>
      </div>
      <div className="w-full max-w-lg">
        <div className="flex flex-wrap -mx-3 mb-6">
          <Button
            disabled={
              !template.name ||
              !template.description ||
              template.name.length < 6 ||
              exist
            }
            onClick={() => save()}
          >
            Save
          </Button>
        </div>
      </div>
    </div>
  );
};

TemplateForm.propTypes = {
  dispatchTemplate: PropTypes.func,
  template: PropTypes.object,
  save: PropTypes.func,
  doesNameExist: PropTypes.func,
  exist: PropTypes.bool,
  isUpdate: PropTypes.bool,
};

export default TemplateForm;
