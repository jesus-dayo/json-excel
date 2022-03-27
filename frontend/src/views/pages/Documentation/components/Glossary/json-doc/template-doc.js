import React from "react";
import addTemplateGIF from "../../../../../../assets/docs/add-template.gif";
import designTemplate from "../../../../../../assets/docs/design-sample-template.png";
import saveTemplate from "../../../../../../assets/docs/save-template.gif";

const templatedoc = {
  name: "templatedoc",
  content: [
    {
      header: "Adding your first template",
      body: (
        <div>
          <p>
            - Templates are created in MS Excel and as of now we only support
            the below versions.
          </p>
          <p className="m-4">
            <ul className="list-disc ">
              <li>Microsoft Excel 2004 (XLS)</li>
              <li>Microsoft Excel 2007 (XLSX)</li>
            </ul>
          </p>
          <p>
            - In your Excel file you may do your usual designs, such as
            coloring, fonts, margins, merge cells and other more.
          </p>
          <img src={designTemplate} alt="design-template"/>
          <p className="mt-4">
            - Once you are done, you can upload the excel file into the template
            manager web application. Click on &apos;Add Template&apos; and
            upload your excel template.
          </p>
          <img src={addTemplateGIF} alt="add-button"/>
          <p className="mt-4">
            - Template names are unique, enter a unique name for your template
            and add a description.
          </p>
          <p>
            - The template manager will convert the excel file into a json
            formatted template which will be used by the report generator when
            generating the reports. You may click on Preview to display the json
            generated.
          </p>
          <p>
            - Once you are done, you can save the template by clicking on the
            Save button.
          </p>
          <img src={saveTemplate} alt="save-template"/>
        </div>
      ),
    },
  ],
};

export {templatedoc};
