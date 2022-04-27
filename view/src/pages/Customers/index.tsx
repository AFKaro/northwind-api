import axios from "axios";
import React, { useState, useEffect } from "react";

import { Button, Modal, Table } from "react-bootstrap";
import EditOutlined from "@material-ui/icons/EditOutlined";
import DeleteOutline from "@material-ui/icons/DeleteOutline";

import NewCustomerModel from "./NewCustomerModal";
import DeleteCustomerModal from "./DeleteCustomerModal";

export type TCustomer = {
    customerID: string;
    companyName: string;
    contactName: string | null;
    contactTitle: string | null;
    address: string | null;
    city: string | null;
    region: string | null;
    postalCode: string | null;
    country: string | null;
    phone: string | null;
    fax: string | null;
};

const CustomersPage: React.FC = () => {
    const [customers, setCustomers] = useState<TCustomer[]>([]);
    const [customerToBeRemoved, setCustomerToBeRemoved] = useState<string | null>(null);
    const [customerToBeUpdated, setCustomerToBeUpdated] = useState<TCustomer | null>(null);

    const fetchCustomers = () => {
        axios.get("http://localhost:9090/customer/all").then((axiosResp) => {
            setCustomers(axiosResp.data.object);
        });
    };

    const handleDeleteCustomer = (deleteSelectedCustomer: boolean) => {
        if (!deleteSelectedCustomer) setCustomerToBeRemoved(null);

        axios
            .delete(`http://localhost:9090/customer/${customerToBeRemoved}`)
            .then((response) => {
                if (response.status >= 200 && response.status <= 299) console.log("Deleted successfully");
            })
            .catch((error) => {
                console.error(error);
            })
            .finally(() => {
                setCustomerToBeRemoved(null);
                fetchCustomers();
            });
    };

    useEffect(() => {
        fetchCustomers();
    }, []);

    return (
        <div>
            <p className="page_title">Customers</p>
            <NewCustomerModel fetchCustomers={fetchCustomers} customerToBeUpdated={customerToBeUpdated} handleOnClose={() => setCustomerToBeUpdated(null)}/>

            <DeleteCustomerModal
                showDeleteCustomerModal={customerToBeRemoved !== null}
                confirmDeletion={handleDeleteCustomer}
            />

            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th></th>
                        <th>Id</th>
                        <th>Company</th>
                        <th>Contact name/title</th>
                        <th>Country</th>
                        <th>Phone</th>
                    </tr>
                </thead>
                <tbody>
                    {customers.map((customer) => (
                        <tr key={customer.customerID} className="table_row align-middle">
                            <td className="col-1 text-center">
                                <Button
                                    className="bg-transparent border-0 text-secondary table_control"
                                    onClick={() => setCustomerToBeUpdated(customer)}
                                >
                                    <EditOutlined />
                                </Button>
                                <Button
                                    className="bg-transparent border-0 text-secondary table_control"
                                    onClick={() => setCustomerToBeRemoved(customer.customerID)}
                                >
                                    <DeleteOutline />
                                </Button>
                            </td>
                            <td>{customer.customerID}</td>
                            <td>{customer.companyName}</td>
                            <td>
                                {customer.contactName}/{customer.contactTitle}
                            </td>
                            <td>{customer.country}</td>
                            <td>{customer.phone}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
};

export default CustomersPage;
