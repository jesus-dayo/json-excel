import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import TemplateForm from "./TemplateForm";

const setup = () => render(<TemplateForm />);

describe("displaying the template form", () => {
  it("should display fields", () => {
    setup();
    expect(screen.queryByLabelText("Name")).toBeInTheDocument();
    // expect(screen.queryByLabelText('Row Mapping')).toBeInTheDocument();
    // expect(screen.queryByText('Add Row')).toBeInTheDocument();
    // expect(screen.queryByLabelText('Global Styles')).toBeInTheDocument();
    // expect(screen.queryByText('Create')).toBeInTheDocument();
    // expect(screen.queryByText('Cancel')).toBeInTheDocument();
  });
});
