import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import PublicLayout from './components/PublicLayout';
import Homepage from './components/Homepage';
import Register from './components/Register';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import Properties from './components/Properties';
import Analytics from './components/Analytics';
import Tenants from './components/Tenants';
import Maintenance from './components/Maintenance';
import './styles/modern.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          {/* Public routes with public layout */}
          <Route path="/" element={
            <PublicLayout>
              <Homepage />
            </PublicLayout>
          } />
          <Route path="/register" element={
            <PublicLayout>
              <Register />
            </PublicLayout>
          } />
          <Route path="/login" element={
            <PublicLayout>
              <Login />
            </PublicLayout>
          } />
          
          {/* Dashboard routes with dashboard layout */}
          <Route path="/dashboard" element={
            <Layout>
              <Dashboard />
            </Layout>
          } />
          <Route path="/properties" element={
            <Layout>
              <Properties />
            </Layout>
          } />
          <Route path="/analytics" element={
            <Layout>
              <Analytics />
            </Layout>
          } />
          <Route path="/tenants" element={
            <Layout>
              <Tenants />
            </Layout>
          } />
          <Route path="/maintenance" element={
            <Layout>
              <Maintenance />
            </Layout>
          } />
        </Routes>
      </div>
    </Router>
  );
}

export default App;