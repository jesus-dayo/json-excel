import PropTypes from "prop-types";
import React,{useEffect,  useState} from "react";
import { deleteTemplate, listTemplates } from "../../../../services/service";
import Button from "../../../../components/Button/Button";
import TemplateTestModal from "./TemplateTest/index";

const TemplateList = ({ handleUpdate }) => {
  const [templates, setTemplates] = useState([]);
  const [testOpen, setTestOpen] = useState();

  const fetchTemplates = async () => {
    try {
      const response = await listTemplates();
      setTemplates(response);
    } catch (e) {
      console.error(e);
      alert("Error fetching templates. Unable to contact server.");
    }
  };

  useEffect(() => {
    fetchTemplates();
  }, []);

  const deleteTemp = async (name) => {
    try {
      await deleteTemplate(name);
      alert(`Delete was successful of template - ${name}.`);
      fetchTemplates();
    } catch (e) {
      console.error(e);
      alert("Error fetching templates. Unable to contact server.");
    }
  };

  const testTemp = (template) => {
    setTestOpen(template);
  };

  return (
    <div className="m-2 w-full h-full">
      <table className="table-auto w-full text-sm">
        <thead>
          <tr className="border-b dark:border-slate-600 font-medium p-4 pl-8 pt-0 pb-3 text-slate-400 dark:text-slate-200 text-left">
            <th width={"20%"}>Name</th>
            <th width={"15%"}>Format</th>
            <th width={"20%"}>Description</th>
            <th width={"10%"}>Action</th>
          </tr>
        </thead>
        <tbody className="bg-white dark:bg-slate-800">
          {templates &&
            templates.map((template, index) => (
              <tr className="text-left" key={`row-${index}`}>
                <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                  {template.name}
                </td>
                <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                  {template.format}
                </td>
                <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                  {template.description}
                </td>
                <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                  <div className="flex gap-2 space-x-8">
                    <Button
                      variant="secondary"
                      onClick={() => handleUpdate(template)}
                    >
                      Update
                    </Button>
                    <Button
                      variant="error"
                      onClick={() => deleteTemp(template.name)}
                    >
                      Delete
                    </Button>
                    <Button
                      variant="tertiary"
                      onClick={() => testTemp(template)}
                    >
                      Test
                    </Button>
                  </div>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
      {testOpen && (
        <TemplateTestModal
          template={testOpen}
          isOpen={testOpen}
          onClose={() => setTestOpen(null)}
        />
      )}
    </div>
  );
};

TemplateList.propTypes = {
  templates: PropTypes.array,
  handleUpdate: PropTypes.func,
};

export default TemplateList;
