import React from "react";

const expressiondoc = {
  name: "expressiondoc",
  content: [
    {
      header: "Expressions - ${}",
      body: (
        <div>
          <p>
            - Expressions are defined string commands in the template that the
            rendering engine understands. The rendering engine will evaluate and
            render the cell based on the expression used.
          </p>
          <p className="mb-2">
            - Each expression has a different purpose, please read docs below
            for each.
          </p>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Object Expressions - $&#123;Client Details:clientCode1&#125;
            </h2>
            <p>- maps to json data</p>
            <p>- if returns a single result , will render that into the cell</p>
            <p>- if returns a list , will render as comma delimited string</p>
          </div>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Row Function Expression - $&#123;row(assetCode#Asset NAV
              Details:assetCode)&#125;
            </h2>
            <p>
              - maps to json data with key identified in string# , in this
              example, the key to get is assetCode.
            </p>
            <p>- read through the whole row and renders each cell</p>
            <p>
              - if cell is not rendered due to dependencies to other cells, it
              will be added to the delayed rendered list, which will be
              processed later once its dependencies are resolved.
            </p>
          </div>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Divide Function Expression - $&#123;divide(J9,J12)&#125;
            </h2>
            <p>
              - accepts 2 parameters, dividend and divisor, it can be an Excel
              cell address or x,y axis 0 index.
            </p>
            <p>- renders the cell with the Excel division formula</p>
          </div>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Total Column Function Expression - $&#123;totalCol(L9)&#125;
            </h2>
            <p>
              - accepts 1 parameter, it can be an Excel cell address or x,y axis
              0 index.
            </p>
            <p>
              - renders the cell with the Excel sum formula which targets a
              specific cell
            </p>
            <p>
              - automatically adds the SUM range for dynamically created rows
              for the target cell
            </p>
          </div>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Sum Function Expression - $&#123;sum(D17,D18)&#125;
            </h2>
            <p>
              - accepts 2 parameters, it can be an Excel cell address or x,y
              axis 0 index.
            </p>
            <p>
              - renders the cell with the Excel sum formula which targets 2
              specific cell
            </p>
          </div>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Reference Function Expression - $&#123;ref(D17)&#125;
            </h2>
            <p>
              - accepts 1 parameter, it can be an Excel cell address or x,y axis
              0 index.
            </p>
            <p>
              - renders the cell with the Excel reference formula (=D17) which
              just refers to a specific cell
            </p>
          </div>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Total Function Expression - $&#123;total(NAV
              Details:clientContribution)&#125;
            </h2>
            <p>- accepts json data mapping.</p>
            <p>
              - renders the cell with the total sum of the json data list
              results
            </p>
          </div>
          <div>
            <h2 className="text-lg text-left font-semibold mt-2 underline">
              Total Negative Function Expression - $&#123;totalNegative(NAV
              Details:netIncreaseDecrease)&#125;
            </h2>
            <p>- accepts json data mapping.</p>
            <p>
              - renders the cell with the total negative sum of the json data
              list results
            </p>
          </div>
        </div>
      ),
    },
  ],
};

export {expressiondoc};
