const templateUrl = "/api/template";
const reportUrl = "/api/report";

const saveTemplate = async (template) => {
  const response = await fetch(`${templateUrl}`, {
    method: "POST",
    body: JSON.stringify(template),
    headers: { "Content-Type": "application/json" },
  });
  return response.json();
};

const listTemplates = async () => {
  const response = await fetch(`${templateUrl}`, {
    method: "GET",
  });
  return response.json();
};

const getTemplate = async (name) => {
  const response = await fetch(`${templateUrl}/${name}`, {
    method: "GET",
  });
  return response;
};

const uploadTemplate = async (template, name, description) => {
  let formData = new FormData();
  formData.append("file", template);
  if (name) {
    formData.append("name", name);
  }
  if (description) {
    formData.append("description", description);
  }
  const response = await fetch(`${templateUrl}/upload`, {
    method: "POST",
    body: formData,
  });
  return response.json();
};

const deleteTemplate = async (name) => {
  const response = await fetch(`${templateUrl}/${name}`, {
    method: "DELETE",
  });
  return response.json();
};

const downloadTemplate = async (name) => {
  const response = await fetch(`${templateUrl}/download/${name}`, {
    method: "POST",
  });
  return response;
};

const generateReport = async (name, data) => {
  const response = await fetch(`${reportUrl}/generate/${name}`, {
    method: "POST",
    body: data,
  });
  return response;
};

const perfTestReport = async (name, data, count = 1) => {
  const response = await fetch(
    `${reportUrl}/performance/generate/${name}/${count}`,
    {
      method: "POST",
      body: data,
    }
  );
  return response.json();
};

const perfList = async (name) => {
  const response = await fetch(`${reportUrl}/performance/${name}`, {
    method: "GET",
  });
  return response.json();
};

export {
  saveTemplate,
  listTemplates,
  uploadTemplate,
  deleteTemplate,
  generateReport,
  getTemplate,
  perfTestReport,
  perfList,
  downloadTemplate,
};
