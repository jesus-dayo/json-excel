import PropTypes from "prop-types";
import React, { useState, useEffect } from "react";
import Button from "../../../../../../components/Button/Button";
import { perfTestReport, perfList } from "../../../../../../services/service";
import { CopyToClipboard } from "react-copy-to-clipboard";
import JsonInputText from "../JsonInputText";

const Performance = ({ template }) => {
  const [json, setJson] = useState();
  const [hasError, setHasError] = useState(false);
  const [setupRun, setSetupRun] = useState(false);
  const [perfData, setPerfData] = useState([]);

  const getList = async () => {
    const list = await perfList(template.name);
    setPerfData(list);
  };

  useEffect(() => {
    getList();
  }, []);

  const handlePerformance = async () => {
    let count = prompt("How many reports? enter valid number", 1);
    const regexNum = new RegExp("^[0-9]*$");
    if (count && regexNum.test(count) && count <= 100) {
      await perfTestReport(template.name, json, count);
      handleShowList();
    } else {
      alert("Not a valid count, max is 100");
    }
  };

  const handleShowList = () => {
    setSetupRun(false);
    getList();
  };

  return (
    <>
      {!setupRun && (
        <div className="m-2 w-full h-full">
          <table className="table-auto w-full text-sm">
            <thead>
              <tr className="border-b dark:border-slate-600 font-medium p-4 pl-8 pt-0 pb-3 text-slate-400 dark:text-slate-200 text-left">
                <th width={"20%"}>Run Date</th>
                <th width={"15%"}>Count</th>
                <th width={"10%"}>Memory Consumption (bytes)</th>
                <th width={"10%"}>Memory Consumption (mb)</th>
                <th width={"10%"}>Execution time (ms)</th>
                <th width={"20%"}></th>
              </tr>
            </thead>
            <tbody className="bg-white dark:bg-slate-800">
              {perfData &&
                perfData.map((perf, index) => (
                  <tr className="text-left" key={`row-${index}`}>
                    <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                      {perf.executedDateTime}
                    </td>
                    <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                      {perf.reportCount}
                    </td>
                    <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                      {perf.memoryInBytes}
                    </td>
                    <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                      {perf.memoryInMB}
                    </td>
                    <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                      {perf.elapsedTimeInMS}
                    </td>
                    <td className="border-b border-slate-100 dark:border-slate-700 p-2">
                      <div className="flex gap-2 space-x-8">
                        <CopyToClipboard
                          className="bg-green-500 hover:cursor-pointer p-2 rounded-md"
                          text={perf.data}
                          onCopy={() =>
                            alert("Copy to Clipboard was successful.")
                          }
                        >
                          <span>Copy test data</span>
                        </CopyToClipboard>
                      </div>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
          <div className="p-2 flex gap-2">
            <Button variant="tertiary" onClick={() => setSetupRun(true)}>
              Setup Test Run
            </Button>
          </div>
        </div>
      )}
      {setupRun && (
        <>
          <div className="p-2">
            <div className="p-2">
              <JsonInputText
                setHasError={setHasError}
                setJson={setJson}
                template={template}
              />
            </div>
          </div>
          <div className="p-2 flex gap-2">
            <Button
              variant="tertiary"
              onClick={handlePerformance}
              disabled={hasError}
            >
              Run Test
            </Button>
            <Button variant="tertiary" onClick={handleShowList}>
              Show History
            </Button>
          </div>
        </>
      )}
    </>
  );
};

Performance.propTypes = {
  customStyles: PropTypes.object,
  template: PropTypes.object,
};

export default Performance;
