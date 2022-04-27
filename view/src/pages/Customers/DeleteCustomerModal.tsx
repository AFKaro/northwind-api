import React from "react";
import { Button, Modal } from "react-bootstrap";

interface props {
    showDeleteCustomerModal: boolean;
    confirmDeletion: (confirm: boolean) => void
}

const DeleteCustomerModal: React.FC<props> = ({ showDeleteCustomerModal, confirmDeletion }) => {
    return (
        <Modal show={showDeleteCustomerModal}>
            <Modal.Header>
                <span>Delete customer?</span>
            </Modal.Header>
            <Modal.Body>
                <span>Do you really want to delete this customer? It can't be undone.</span>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={() => confirmDeletion(true)}>YES</Button>
                <Button onClick={() => confirmDeletion(false)}>No</Button>
            </Modal.Footer>
        </Modal>
    );
};

export default DeleteCustomerModal;
