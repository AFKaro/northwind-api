import axios from "axios";
import React, { useEffect, useState } from "react";
import { Table } from "react-bootstrap";

type TOrder = {
    orderID: number;
    customerID: string;
    employeeID: number;
    orderDate: string;
    requiredDate: string;
    shippedDate: string;
    shipVia: number;
    freight: number;
    shipName: string;
    shipAddress: string;
    shipCity: string;
    shipRegion: null;
    shipPostalCode: string;
    shipCountry: string;
};


const OrdersPage: React.FC = () => {
    const [orders, setOrders] = useState<TOrder[]>([]);

    const fetchOrders = () => {
        axios.get("http://localhost:9090/order/all").then((axiosResp) => {
            setOrders(axiosResp.data.object);
        });
    };

    useEffect(() => {
        fetchOrders();
    }, []);

    return (
        <div>
            <p className="page_title">Orders</p>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Customer</th>
                        <th>Shipped date</th>
                        <th>Ship name</th>
                        <th>Ship address/Postal code</th>
                        <th>Ship city</th>
                        <th>Ship Country</th>
                    </tr>
                </thead>
                <tbody>
                    {orders.map((order) => (
                        <tr key={order.orderID}>
                            <td>{order.orderID}</td>
                            <td>{order.customerID}</td>
                            <td>{order.shippedDate}</td>
                            <td>{order.shipName}</td>
                            <td>
                                `${order.shipAddress}/${order.shipPostalCode}`
                            </td>
                            <td>{order.shipCity}</td>
                            <td>{order.shipCountry}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
};

export default OrdersPage;
