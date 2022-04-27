import axios from "axios";
import React, { useEffect, useState, useRef } from "react";
import { Button, Modal } from "react-bootstrap";
import { TCustomer } from ".";

interface props {
    fetchCustomers: () => void;
    customerToBeUpdated: TCustomer | null;
    handleOnClose: () => void;
}

const NewCustomerModel: React.FC<props> = ({ fetchCustomers, customerToBeUpdated, handleOnClose }) => {
    const newUserForm = useRef<HTMLFormElement | null>(null);
    const inCompanyName = useRef<HTMLInputElement | null>(null);
    const inContactName = useRef<HTMLInputElement | null>(null);
    const inContactTitle = useRef<HTMLInputElement | null>(null);
    const inCountry = useRef<HTMLInputElement | null>(null);
    const inPostalCode = useRef<HTMLInputElement | null>(null);
    const inCity = useRef<HTMLInputElement | null>(null);
    const inAddress = useRef<HTMLInputElement | null>(null);
    const inPhone = useRef<HTMLInputElement | null>(null);
    const inFAX = useRef<HTMLInputElement | null>(null);

    const [showNewCustomerModal, setShowNewCustomerModal] = useState(false);

    const handleOpenNewCustomerModal = () => setShowNewCustomerModal(true);
    const handleCloseNewCustomerModal = () => {
        setShowNewCustomerModal(false)
        handleOnClose()
    }

    useEffect(() => {
        setShowNewCustomerModal(customerToBeUpdated !== null)
    }, [customerToBeUpdated])

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        e.stopPropagation();

        const requestFunction = customerToBeUpdated ? axios.put : axios.post

        if (newUserForm.current?.checkValidity()) {
            const data = {
                companyName: inCompanyName.current?.value,
                contactName: inContactName.current?.value,
                contactTitle: inContactTitle.current?.value,
                address: inAddress.current?.value,
                city: inCity.current?.value,
                postalCode: inPostalCode.current?.value,
                country: inCountry.current?.value,
                phone: inPhone.current?.value,
                fax: inFAX.current?.value.trim() !== "" ? inFAX.current?.value : null,
            };
            console.log(`Sending`);
            console.log(data);

            requestFunction(`http://localhost:9090/customer/${customerToBeUpdated?.customerID || ''}`, data).then((response) => {
                console.log(response);
                if (response.status >= 200 && response.status <= 300) {
                    handleCloseNewCustomerModal();
                    fetchCustomers();
                }
            })
            .catch(error => console.log(error))
        }

        newUserForm.current?.classList.add("was-validated");
    };

    return (
        <>
            <Button className="mb-4" onClick={handleOpenNewCustomerModal}>
                Add new customer
            </Button>

            <Modal show={showNewCustomerModal} onHide={handleCloseNewCustomerModal} centered>
                <Modal.Header closeButton>
                    <Modal.Title>New customer</Modal.Title>
                </Modal.Header>
                <form ref={newUserForm} onSubmit={handleSubmit} noValidate className="needs-validation">
                    <Modal.Body>
                        <div className="mb-3 row">
                            <div className="col-sm">
                                <label htmlFor="in_companyName">Company name</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inCompanyName}
                                    type="text"
                                    className="form-control"
                                    id="in_companyName"
                                    defaultValue={customerToBeUpdated?.companyName}
                                    required
                                />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_contactTitle">Contact name</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inContactName}
                                    type="text"
                                    className="form-control"
                                    id="in_contactName"
                                    defaultValue={customerToBeUpdated?.contactName || ""}
                                    required
                                />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_contactTitle">Contact title</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inContactTitle}
                                    type="text"
                                    className="form-control"
                                    id="in_contactTitle"
                                    defaultValue={customerToBeUpdated?.contactTitle || ""}
                                    required
                                />
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <div className="col-sm">
                                <label htmlFor="in_country">Country</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inCountry}
                                    type="text"
                                    className="form-control"
                                    id="in_country"
                                    defaultValue={customerToBeUpdated?.country || ""}
                                    required
                                />
                            </div>

                            <div className="col-sm">
                                <label htmlFor="in_postalCode">Postal code</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inPostalCode}
                                    type="text"
                                    className="form-control"
                                    id="in_postalCode"
                                    defaultValue={customerToBeUpdated?.postalCode || ""}
                                    required
                                />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_city">City</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inCity}
                                    type="text"
                                    className="form-control"
                                    id="in_city"
                                    required
                                    defaultValue={customerToBeUpdated?.city || ""}
                                />
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <div className="col-sm">
                                <label htmlFor="in_address">Address</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inAddress}
                                    type="text"
                                    className="form-control"
                                    id="in_address"
                                    required
                                    defaultValue={customerToBeUpdated?.address || ""}
                                />
                            </div>
                        </div>

                        <div className="mb-3 row">
                            <div className="col-sm">
                                <label htmlFor="inPhone">Phone</label>
                                <span className="badge text-danger">*</span>
                                <input
                                    ref={inPhone}
                                    type="text"
                                    className="form-control"
                                    id="inPhone"
                                    required
                                    defaultValue={customerToBeUpdated?.phone || ""}
                                />
                            </div>
                            <div className="col-sm">
                                <label htmlFor="in_fax">FAX</label>
                                <input
                                    ref={inFAX}
                                    type="text"
                                    className="form-control"
                                    id="in_fax"
                                    defaultValue={customerToBeUpdated?.fax || ""}
                                />
                            </div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="primary" type="submit">
                            Submit
                        </Button>
                    </Modal.Footer>
                </form>
            </Modal>
        </>
    );
};

export default NewCustomerModel;
