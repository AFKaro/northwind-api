import React, { useCallback, useEffect, useState } from "react";
import axios from "axios";
import { Card } from "react-bootstrap";

export interface TCustomerReport {
    customerID: string;
    customerName: string;
    quantity_orders: number;
    quantity_products: number;
    amountSpent: number;
}

const HomePage: React.FC = () => {
    const [customersReport, setCustomersReport] = useState<TCustomerReport[]>([]);
    const [customerThatSpentTheMost, setCustomerThatSpentTheMost] = useState<TCustomerReport | null>(null);
    const [customerThatOrderedTheMost, setCustomerThatOrderedTheMost] = useState<TCustomerReport | null>(null);
    const [customerHaveBoughtMoreProducts, setCustomerHaveBoughtMoreProducts] = useState<TCustomerReport | null>(null);

    const getCustomerThatSpentTheMost = useCallback((): TCustomerReport | null => {
        let customer: TCustomerReport | null = null;
        customersReport.forEach((c) => {
            if (!customer || c.amountSpent > customer.amountSpent) customer = c;
        });

        return customer;
    }, [customersReport]);

    const getCustomerThatOrderedTheMost = useCallback((): TCustomerReport | null => {
        let customer: TCustomerReport | null = null;
        customersReport.forEach((c) => {
            if (!customer || c.quantity_orders > customer.quantity_orders) customer = c;
        });

        return customer;
    }, [customersReport]);

    const getCustomerThatHaveBoughtMoreProducts = useCallback((): TCustomerReport | null => {
        let customer: TCustomerReport | null = null;
        customersReport.forEach((c) => {
            if (!customer || c.quantity_products > customer.quantity_products) customer = c;
        });

        return customer;
    }, [customersReport]);

    useEffect(() => {
        if (!customersReport) return;

        let customerThatSpentTheMost = getCustomerThatSpentTheMost();
        let customerThatOrderedTheMost = getCustomerThatOrderedTheMost();
        let customerHaveBoughtMoreProducts = getCustomerThatHaveBoughtMoreProducts();

        if (customerThatSpentTheMost) setCustomerThatSpentTheMost(customerThatSpentTheMost);
        if (customerThatOrderedTheMost) setCustomerThatOrderedTheMost(customerThatOrderedTheMost);
        if (customerHaveBoughtMoreProducts) setCustomerHaveBoughtMoreProducts(customerHaveBoughtMoreProducts);
    }, [customersReport, getCustomerThatHaveBoughtMoreProducts, getCustomerThatOrderedTheMost, getCustomerThatSpentTheMost]);

    useEffect(() => {
        axios.get("http://localhost:9090/customer/report").then((response) => {
            setCustomersReport(response.data.object);
        });
    }, []);

    return (
        <div>
            <p className="page_title">Relatório</p>
            <div className="row">
                {customerThatSpentTheMost ? (
                    <Card className="col-md-auto" style={{ maxWidth: "350px", maxHeight: "200px" }}>
                        <Card.Header className="text-primary">Customer that spent the most</Card.Header>
                        <Card.Body>
                            <span className="d-block">{`Name: ${customerThatSpentTheMost.customerName}`}</span>
                            <span className="d-block text-primary">{`Ammount spent ($): ${customerThatSpentTheMost.amountSpent}`}</span>
                            <span className="d-block">{`Orders: ${customerThatSpentTheMost.quantity_orders}`}</span>
                            <span className="d-block">{`Products bought: ${customerThatSpentTheMost.quantity_products}`}</span>
                        </Card.Body>
                    </Card>
                ) : (
                    ``
                )}

                {customerThatOrderedTheMost ? (
                    <Card className="col-md-auto ms-5 me-5" style={{ maxWidth: "350px", maxHeight: "200px" }}>
                        <Card.Header className="text-primary">Customer that ordered the most</Card.Header>
                        <Card.Body>
                            <span className="d-block">{`Name: ${customerThatOrderedTheMost.customerName}`}</span>
                            <span className="d-block text-primary">{`Orders: ${customerThatOrderedTheMost.quantity_orders}`}</span>
                            <span className="d-block">{`Ammount spent ($): ${customerThatOrderedTheMost.amountSpent}`}</span>
                            <span className="d-block">{`Products bought: ${customerThatOrderedTheMost.quantity_products}`}</span>
                        </Card.Body>
                    </Card>
                ) : (
                    ``
                )}

                {customerHaveBoughtMoreProducts ? (
                    <Card className="col-md-auto" style={{ maxWidth: "350px", maxHeight: "200px" }}>
                        <Card.Header className="text-primary">Customer that bought most products</Card.Header>
                        <Card.Body>
                            <span className="d-block">{`Name: ${customerHaveBoughtMoreProducts.customerName}`}</span>
                            <span className="d-block text-primary">{`Products bought: ${customerHaveBoughtMoreProducts.quantity_products}`}</span>
                            <span className="d-block">{`Ammount spent ($): ${customerHaveBoughtMoreProducts.amountSpent}`}</span>
                            <span className="d-block">{`Orders: ${customerHaveBoughtMoreProducts.quantity_orders}`}</span>
                        </Card.Body>
                    </Card>
                ) : (
                    ``
                )}
            </div>
        </div>
    );
};

export default HomePage;
