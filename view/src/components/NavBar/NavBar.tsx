import React from "react";
import { Link } from "react-router-dom";

import { Navbar, Container, Nav } from "react-bootstrap";

import HomeOutlined from "@material-ui/icons/HomeOutlined";
import ReceiptOutlined from "@material-ui/icons/ReceiptOutlined";
import Group from "@material-ui/icons/Group";

import "./navBarStyles.scss";

const NavBar = () => {
    return (
        <Navbar style={{ width: "150px" }} className="p-0 vh-100 border-end sticky-top">
            <Container className="d-flex flex-column h-100 p-0">
                <Navbar.Brand href="/" id="navbar_brand">
                    <img src="https://images.vexels.com/media/users/3/200097/isolated/preview/942820836246f08c2d6be20a45a84139-carrinho-de-compras-icon-carrinho-de-compras.png" alt="store_logo" style={{width: "64px"}}/>
                </Navbar.Brand>
                <Nav className="w-100 h-100 d-flex align-items-center justify-content-center flex-column">
                    <Link to={"/"} className="nav-link mt-4">
                        <HomeOutlined />
                    </Link>
                    <Link to={"/orders"} className="nav-link mt-4">
                        <ReceiptOutlined />
                    </Link>
                    <Link to={"/customers"} className="nav-link mt-4">
                        <Group />
                    </Link>                    
                </Nav>
            </Container>
        </Navbar>
    );
};

export default NavBar;
