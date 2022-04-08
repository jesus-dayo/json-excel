import PropTypes from "prop-types";
import React from "react";
import Divider from "../../../../../components/Divider/Divider";

const Document = ({doc}) => {
  return (
    <div className="mt-2 ml-10 mr-10">
      {doc.content.map((cont) => (
        <div key={doc.name}>
          <h2 className="text-lg text-left font-semibold mt-2">
            {cont.header}
          </h2>
          <Divider/>
          <div className="text-left">{cont.body}</div>
        </div>
      ))}
    </div>
  );
};

Document.propTypes = {
  doc: PropTypes.object.isRequired,
};

export default Document;
