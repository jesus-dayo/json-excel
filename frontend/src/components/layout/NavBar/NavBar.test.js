import React from 'react';
import {render, screen} from '@testing-library/react';
import '@testing-library/jest-dom'

import NavBar from './NavBar';

const TEST_NAV_BAR_ID = 'test-nav-bar';

const setup = () => render(<NavBar data-testid='test-nav-bar'/>);

describe('displaying the navbar',()=>{
    it('should display details in navbar',()=>{
        setup();
        expect(screen.queryByTestId(TEST_NAV_BAR_ID)).toBeInTheDocument();
    })
})