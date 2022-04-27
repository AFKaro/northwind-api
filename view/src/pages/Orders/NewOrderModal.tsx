import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import { Button, Modal, Table } from "react-bootstrap";

export type TOrder = {
    orderID: number;
    customerID: string;
    employeeID: number;
    orderDetails: TOrderProduct[];
    orderDate: string;
    requiredDate: string | null;
    shippedDate: string | null;
    shipVia: number | null;
    freight: number;
    shipName: string | null;
    shipAddress: string;
    shipCity: string;
    shipRegion: null | null;
    shipPostalCode: string;
    shipCountry: string;
};

export type TOrderProduct = {
    productID: number;
    unitPrice: number;
    quantity: number;
    discount: number;
};

export type TProduct = {
    productID: number;
    productName: string;
    supplierID: number;
    categoryID: number;
    quantityPerUnit: string;
    unitPrice: number;
    unitsInStock: number;
    unitsOnOrder: number;
    reorderLevel: number;
    discontinued: number;
};

interface props {
    fetchOrders: () => void;
}

const NewOrderModal: React.FC<props> = ({ fetchOrders }) => {
    const inEmployeeID = useRef<HTMLInputElement | null>(null);
    const inCustomerID = useRef<HTMLInputElement | null>(null);
    const inFreight = useRef<HTMLInputElement | null>(null);
    const inShipAddress = useRef<HTMLInputElement | null>(null);
    const inShipCity = useRef<HTMLInputElement | null>(null);
    const inShipPostalCode = useRef<HTMLInputElement | null>(null);
    const inShipCountry = useRef<HTMLInputElement | null>(null);
    const slctProduct = useRef<HTMLSelectElement | null>(null);

    const [showModal, setShowModal] = useState(false);
    const [products, setProducts] = useState<TProduct[]>([]);
    const [orderProducts, setOrderProducts] = useState<TOrderProduct[]>([]);

    const getProductName = (productId: number) => {
        return products.filter((p) => p.productID === productId)[0].productName;
    };

    const getProductById = (productId: number) => {
        return products.filter((p) => p.productID === productId)[0];
    };

    const getOrderProductById = (productId: number) => {
        return orderProducts.filter((p) => p.productID === productId)[0];
    };

    const handleProductDiscountChange = (e: React.ChangeEvent<HTMLInputElement>, productId: number) => {
        const _orderProducts: TOrderProduct[] = JSON.parse(JSON.stringify(orderProducts));
        _orderProducts.forEach((product) => {
            if (product.productID === productId) product.discount = parseFloat(e.target.value);
        });
        setOrderProducts(_orderProducts);
    };

    const updateProductQuantity = (e: React.ChangeEvent<HTMLInputElement>, productId: number) => {
        let _orderProducts: TOrderProduct[] = JSON.parse(JSON.stringify(orderProducts));
        const newQuantity = parseFloat(e.target.value);

        if (newQuantity === 0) _orderProducts = _orderProducts.filter((p) => p.productID !== productId);
        else
            _orderProducts.forEach((product) => {
                if (product.productID === productId) product.quantity = newQuantity;
            });

        setOrderProducts(_orderProducts);
    };

    const addOrderProduct = () => {
        if (!slctProduct.current) return;
        const productId = Number(slctProduct.current.value);
        const orderProduct = getOrderProductById(productId);
        const _orderProducts: TOrderProduct[] = JSON.parse(JSON.stringify(orderProducts));
        if (orderProduct) {
            _orderProducts.forEach((op: TOrderProduct) => {
                if (op.productID === productId) op.quantity += 1;
            });
        } else {
            _orderProducts.push({
                productID: productId,
                unitPrice: getProductById(productId).unitPrice,
                quantity: 1,
                discount: 0,
            });
        }

        setOrderProducts(_orderProducts);
    };

    const clearFields = () => {
        if (
            !inEmployeeID.current ||
            !inCustomerID.current ||
            !inEmployeeID.current ||
            !inFreight.current ||
            !inShipAddress.current ||
            !inShipCity.current ||
            !inShipPostalCode.current ||
            !inShipCountry.current ||
            !slctProduct.current
        )
            return;

        inEmployeeID.current.value = "";
        inCustomerID.current.value = "";
        inFreight.current.value = "";
        inShipAddress.current.value = "";
        inShipCity.current.value = "";
        inShipPostalCode.current.value = "";
        inShipCountry.current.value = "";
        slctProduct.current.value = "";

        setOrderProducts([]);
    };

    const placeOrder = () => {
        const order = {
            customerID: inCustomerID.current?.value,
            employeeID: inEmployeeID.current?.value,
            orderDetails: orderProducts,
            orderDate: new Date(),
            freight: inFreight.current?.value,
            shipAddress: inShipAddress.current?.value,
            shipCity: inShipCity.current?.value,
            shipPostalCode: inShipPostalCode.current?.value,
            shipCountry: inShipCountry.current?.value,
        };
        axios.post("http://localhost:9090/order/", { ...order }).then((response) => {
            console.log(response);
            clearFields();
            fetchOrders();
            handleCloseModel();
        });
    };

    const handleCloseModel = () => setShowModal(false);

    const fetchAllAvailableProducts = () => {
        axios.get("http://localhost:9090/product/all").then((response) => {
            if (response.status >= 200 && response.status <= 299) setProducts(response.data.object);
        });
    };

    useEffect(() => {
        if (showModal) {
            fetchAllAvailableProducts();
        }
    }, [showModal]);

    return (
        <>
            <Button onClick={() => setShowModal(true)} className="mb-4">Add order</Button>
            <Modal show={showModal} onHide={handleCloseModel} centered>
                <Modal.Header closeButton>
                    <Modal.Title>New order</Modal.Title>
                </Modal.Header>
                <form>
                    <Modal.Body>
                        <div className="mb-3 row">
                            <div className="col-sm">
                                <label htmlFor="in_contactTitle">Employee id</label>
                                <span className="badge text-danger">*</span>
                                <input ref={inEmployeeID} type="text" className="form-control" id="in_employeeId" required />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_customerId">Customer ID</label>
                                <span className="badge text-danger">*</span>
                                <input ref={inCustomerID} type="text" className="form-control" id="in_customerId" required />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_freight">Freight</label>
                                <span className="badge text-danger"></span>
                                <input ref={inFreight} type="text" className="form-control" id="in_freight" required />
                            </div>
                        </div>
                        <div className="mb-3 row">
                            <div className="col-sm">
                                <label htmlFor="in_shipAddress">Address</label>
                                <span className="badge text-danger">*</span>
                                <input ref={inShipAddress} type="text" className="form-control" id="in_shipAddress" required />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_shipCity">City</label>
                                <span className="badge text-danger">*</span>
                                <input ref={inShipCity} type="text" className="form-control" id="in_shipCity" required />
                            </div>
                        </div>
                        <div className="mb-3 row">
                            <div className="col-sm">
                                <label htmlFor="in_postalCode">Postal code</label>
                                <span className="badge text-danger">*</span>
                                <input ref={inShipPostalCode} type="text" className="form-control" id="in_postalCode" required />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_shipCountry">Country</label>
                                <span className="badge text-danger">*</span>
                                <input ref={inShipCountry} type="text" className="form-control" id="in_shipCountry" required />
                            </div>
                        </div>
                        <div className="mb-3">
                            <p>Add a new product to the order</p>
                            <select ref={slctProduct} className="form-select" aria-label="Default select example" defaultValue="none">
                                <option value="none">Select a product</option>
                                {products.map((product) => (
                                    <option key={product.productID} value={product.productID}>
                                        {product.productName}
                                    </option>
                                ))}
                            </select>
                            <Button className="mt-3" onClick={addOrderProduct}>
                                Add product
                            </Button>
                        </div>
                        <div className="mb-3">
                            <p>Order products</p>
                            <Table striped bordered hover>
                                <thead>
                                    <tr>
                                        <th>Product</th>
                                        <th>Unit price ($)</th>
                                        <th>Quantity</th>
                                        <th>Discount p/ unit ($)</th>
                                        <th>Total ($)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {orderProducts.map((orderProduct) => (
                                        <tr key={orderProduct.productID} className="align-middle">
                                            <td>{getProductName(orderProduct.productID)}</td>
                                            <td>{orderProduct.unitPrice}</td>
                                            <td>
                                                <input
                                                    type="text"
                                                    id={`in_order_product_${orderProduct.productID}`}
                                                    className="form-control border-0"
                                                    value={orderProduct.quantity}
                                                    onChange={(e) => updateProductQuantity(e, orderProduct.productID)}
                                                />
                                            </td>
                                            <td>
                                                <input
                                                    type="text"
                                                    id={`in_order_product_${orderProduct.productID}`}
                                                    className="form-control border-0"
                                                    defaultValue={parseFloat("" + orderProduct.discount).toFixed(2)}
                                                    onChange={(e) => handleProductDiscountChange(e, orderProduct.productID)}
                                                />
                                            </td>
                                            <td>{(orderProduct.unitPrice - orderProduct.discount) * orderProduct.quantity}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </Table>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={placeOrder}>Place order</Button>
                    </Modal.Footer>
                </form>
            </Modal>
        </>
    );
};

export default NewOrderModal;
