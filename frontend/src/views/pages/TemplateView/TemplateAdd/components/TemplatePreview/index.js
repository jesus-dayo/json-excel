import PropTypes from "prop-types";
import React, { useRef, useEffect } from "react";
import { prettyPrintJson } from "pretty-print-json";
import { CopyToClipboard } from "react-copy-to-clipboard";

const TemplatePreview = ({ template = {} }) => {
  const jsonContainer = useRef(null);

  useEffect(() => {
    jsonContainer.current.innerHTML = prettyPrintJson.toHtml(template);
  });

  return (
    <div className="flex-grow w-full text-left p-4 border-l-2 border-solid">
      <text className="text-lg font-bold">Preview</text>
      <text> - This is a preview of your report</text>
      <div className="p-2">
        <CopyToClipboard
          className="bg-green-500 hover:cursor-pointer p-2 rounded-md"
          text={JSON.stringify(template)}
          onCopy={() => alert("Copy to Clipboard was successful.")}
        >
          <span>Copy JSON to Clipboard</span>
        </CopyToClipboard>
      </div>
      <div className="border-gray-400 border-solid">
        <div className="w-full text-left mt-2">
          <pre
            className="relative rounded-xl overflow-auto p-4"
            ref={jsonContainer}
          ></pre>
        </div>
      </div>
    </div>
  );
};

TemplatePreview.propTypes = {
  template: PropTypes.object,
};

export default TemplatePreview;
