import PropTypes from "prop-types";
import React, { useReducer, useState } from "react";
import Button from "../../../../components/Button/Button";
import { getTemplate, saveTemplate } from "../../../../services/service";
import TemplateForm from "./components/TemplateForm";
import TemplatePreview from "./components/TemplatePreview";
import { EXCEL_2007 } from "../../../../enums/fileFormats";

const initialTemplate = {
  name: null,
  description: null,
  format: EXCEL_2007,
  sheets: [],
  createdBy: "Jed Dayo",
};

const initTemplate = (initialValues) => {
  return { ...initialValues };
};

const UPDATE = "UPDATE";
const UPDATE_ROW = "UPDATE_ROW";
const ADD_ROW = "ADD_ROW";
const DELETE_ROW = "DELETE_ROW";
const MOVE_COLUMN_DOWN = "MOVE_COLUMN_DOWN";
const MOVE_COLUMN_UP = "MOVE_COLUMN_UP";
const RESET = "RESET";
const JSON = "JSON";

const reducer = (state, action) => {
  switch (action.type) {
    case UPDATE:
      return { ...state, ...action.payload };
    case UPDATE_ROW: {
      const { index, key, value, parent } = action.payload;
      const newRows = [...state[parent]];
      newRows[index][key] = value;
      const newState = { ...state };
      newState[parent] = newRows;
      return { ...state, ...newState };
    }
    case ADD_ROW: {
      const { parent, defaultRow } = action.payload;
      const newState = { ...state };
      newState[parent] = [...state[parent], defaultRow];
      return {
        ...newState,
      };
    }
    case DELETE_ROW: {
      const { index, parent } = action.payload;
      const newRows = [...state[parent]];
      const newState = { ...state };
      newRows.splice(index, 1);
      newState[parent] = [...newRows];
      return {
        ...newState,
      };
    }
    case MOVE_COLUMN_DOWN: {
      const { index, parent } = action.payload;
      const newRows = [...state[parent]];
      newRows[index].index = newRows[index].index + 1;
      newRows[index + 1].index = newRows[index + 1].index - 1;
      const temp = newRows[index];
      newRows[index] = newRows[index + 1];
      newRows[index + 1] = temp;
      const newState = { ...state };
      newState[parent] = [...newRows];
      return {
        ...newState,
      };
    }
    case MOVE_COLUMN_UP: {
      const { index, parent } = action.payload;
      const newRows = [...state[parent]];
      newRows[index].index = newRows[index].index - 1;
      newRows[index - 1].index = newRows[index - 1].index + 1;
      const temp = newRows[index];
      newRows[index] = newRows[index - 1];
      newRows[index - 1] = temp;
      const newState = { ...state };
      newState[parent] = [...newRows];
      return {
        ...newState,
      };
    }
    case RESET: {
      return {
        ...initTemplate(initialTemplate),
      };
    }
    case JSON: {
      return {
        ...state,
        ...action.payload,
      };
    }
    default:
      throw new Error();
  }
};

const TemplateAdd = ({ back, existingTemplate, isUpdate }) => {
  const [template, dispatchTemplate] = useReducer(
    reducer,
    existingTemplate || initialTemplate,
    initTemplate
  );
  const [showPreview, setShowPreview] = useState(false);
  const [exist, setExist] = useState();

  const doesNameExist = async (name) => {
    try {
      const response = await getTemplate(name);
      if (response.status === 200) {
        setExist(true);
      } else {
        setExist(false);
      }
    } catch (e) {
      setExist(false);
    }
  };

  const save = async () => {
    try {
      await saveTemplate(template);
      back();
      dispatchTemplate({ type: RESET });
    } catch (e) {
      console.error(e);
      alert("Error saving the template. Unable to contact server.");
    }
  };
  return (
    <div className="m-2 w-full h-full">
      <div className="w-full p-2 h-full border-l border-gray-200 ">
        <div className="w-full text-right pr-4">
          <Button onClick={() => setShowPreview(!showPreview)}>{`${
            showPreview ? "Close Preview" : "Open Preview"
          }`}</Button>
        </div>
      </div>
      <div className="m-2 flex w-full h-full">
        <TemplateForm
          template={template}
          dispatchTemplate={dispatchTemplate}
          save={save}
          doesNameExist={doesNameExist}
          exist={exist}
          isUpdate={isUpdate}
        />
        {showPreview && <TemplatePreview template={template} />}
      </div>
    </div>
  );
};

TemplateAdd.propTypes = {
  back: PropTypes.func,
  existingTemplate: PropTypes.object,
  isUpdate: PropTypes.bool,
};

export default TemplateAdd;
