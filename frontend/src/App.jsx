import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import ListProduct from "./components/ProductList";
import ProductForm from "./components/ProductForm";
import Login from "./components/Login";
import Register from "./components/Register";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useState, useEffect } from "react";

const PrivateRoute = ({ element, isAuthenticated }) => {
  return isAuthenticated ? element : <Navigate to="/login" replace />;
};

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(() => {
    return localStorage.getItem("isAuthenticated") === "true";
  });

  const handleLogin = () => {
    setIsAuthenticated(true);
    localStorage.setItem("isAuthenticated", "true");
  };

  const handleLogout = () => {
    setIsAuthenticated(false);
    localStorage.removeItem("isAuthenticated");
  };

  return (
    <BrowserRouter>
      <Header isAuthenticated={isAuthenticated} handleLogout={handleLogout} />
      <Routes>
        <Route path="/" element={isAuthenticated ? <ListProduct /> : <Navigate to="/login" replace />} />
        <Route path="/login" element={<Login setIsAuthenticated={handleLogin} />} />
        <Route path="/register" element={<Register />} />
        <Route path="/add-product" element={<PrivateRoute element={<ProductForm />} isAuthenticated={isAuthenticated} />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;