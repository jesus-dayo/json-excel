import React, { useState } from "react";
import RightActionBar from "../../../components/layout/RightActionBar";
import Button from "../../../components/Button/Button";
import TemplateAdd from "./TemplateAdd/index";
import TemplateList from "./TemplateList";

const TemplateView = () => {
  const [showAddUpdate, setShowAddUpdate] = useState();
  const [updateTemplate, setUpdateTemplate] = useState();

  const handleUpdate = (template) => {
    setUpdateTemplate(template);
    setShowAddUpdate(true);
  };

  return (
    <div>
      <div className="w-full">
        <RightActionBar>
          <Button
            onClick={() => setShowAddUpdate(!showAddUpdate)}
            variant="secondary"
          >{`${showAddUpdate ? "Back to Dashboard" : "Add Template"}`}</Button>
        </RightActionBar>
      </div>
      {showAddUpdate && (
        <TemplateAdd
          back={() => setShowAddUpdate(false)}
          existingTemplate={updateTemplate}
        />
      )}
      {!showAddUpdate && <TemplateList handleUpdate={handleUpdate} />}
    </div>
  );
};

export default TemplateView;
