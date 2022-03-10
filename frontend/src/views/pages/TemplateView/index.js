import React, { useState } from "react";
import RightActionBar from "../../../components/layout/RightActionBar";
import Button from "../../../components/Button/Button";
import TemplateAdd from "./TemplateAdd/index";
import TemplateList from "./TemplateList";

const TemplateView = () => {
  const [showAddUpdate, setShowAddUpdate] = useState();
  const [updateTemplate, setUpdateTemplate] = useState();
  const [isUpdate, setIsUpdate] = useState();

  const handleAdd = () => {
    if (showAddUpdate) {
      setShowAddUpdate(false);
    } else {
      setShowAddUpdate(true);
      setIsUpdate(false);
      setUpdateTemplate(null);
    }
  };

  const handleUpdate = (template) => {
    setUpdateTemplate(template);
    setShowAddUpdate(true);
    setIsUpdate(true);
  };

  return (
    <div>
      <div className="w-full">
        <RightActionBar>
          <Button onClick={handleAdd} variant="secondary">{`${
            showAddUpdate ? "Back to Dashboard" : "Add Template"
          }`}</Button>
        </RightActionBar>
      </div>
      {showAddUpdate && (
        <TemplateAdd
          back={() => setShowAddUpdate(false)}
          existingTemplate={updateTemplate}
          isUpdate={isUpdate}
        />
      )}
      {!showAddUpdate && <TemplateList handleUpdate={handleUpdate} />}
    </div>
  );
};

export default TemplateView;
