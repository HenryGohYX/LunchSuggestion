import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LunchMenu from './LunchMenu';
import LunchEdit from './LunchEdit';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/lunch-menu' exact={true} element={<LunchMenu/>}/>
        <Route path='/lunch-edit/:id' element={<LunchEdit/>}/>
      </Routes>
    </Router>
  )
}

export default App;