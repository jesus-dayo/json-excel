import React, { useEffect } from 'react';
import './App.css';
import TemplateView from './views/pages/TemplateView';
import { Routes, Route } from 'react-router-dom';
import NavBar from './components/layout/NavBar/NavBar';
import Container from './components/layout/Container/Container';
import Divider from './components/Divider/Divider';
import { useNavigate } from 'react-router-dom';

const App = () => {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('/');
  }, []);

  return (
    <div className="App">
      <NavBar />
      <Divider />
      <Container>
        <Routes>
          <Route exact path="/" element={<TemplateView />} />
        </Routes>
      </Container>
    </div>
  );
};

export default App;
