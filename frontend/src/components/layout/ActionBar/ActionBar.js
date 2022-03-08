import React, { useState } from "react";
import LinkButton from "../../LinkButton/LinkButton";

const actions = [
  {
    route: "/",
    label: "View Templates",
  },
];

const ActionBar = () => {
  const [activeAction, setActiveAction] = useState(0);

  return (
    <div className="w-full flex mt-5">
      {actions.map((action, i) => (
        <LinkButton
          key={`action-${i}`}
          route={action.route}
          active={activeAction === i}
          onClick={() => setActiveAction(i)}
        >
          {action.label}
        </LinkButton>
      ))}
    </div>
  );
};

export default ActionBar;
