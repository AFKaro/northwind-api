import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import NavBar from "./components/NavBar/NavBar";
import CustomersPage from "./pages/Customers";
import OrdersPage from "./pages/Orders";

import "./styles/App.scss";

const App: React.FC = () => {
    return (
        <BrowserRouter>
            <NavBar />
            <Routes>
                <Route path="/orders" element={<OrdersPage />} />
                <Route path="/customers" element={<CustomersPage />} />
            </Routes>
        </BrowserRouter>
    );
};

export default App;
