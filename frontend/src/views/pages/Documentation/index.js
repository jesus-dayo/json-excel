import React, {useState} from "react";
import Container from "../../../components/layout/Container/Container";
import Document from "./components/Document";
import Glosarry from "./components/Glossary/index";
import {templatedoc} from "./components/Glossary/json-doc/template-doc";

const Documentation = () => {
  const [doc, setDoc] = useState(templatedoc);

  return (
    <div className="w-full">
      <div className="flex w-full">
        <div className="w-60">
          <Glosarry setDoc={setDoc}/>
        </div>
        <div className="w-full">
          <Container>
            <Document doc={doc}/>
          </Container>
        </div>
      </div>
    </div>
  );
};

export default Documentation;
