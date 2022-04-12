package unicap.br.northwind.dtos.mapper;

import unicap.br.northwind.domain.models.Order;
import unicap.br.northwind.dtos.request.OrderRequest;


public class OrderMapper {

    public void map(OrderRequest orderRequest, Order order) {
        order.setCustomerID(orderRequest.getCustomerID());
        order.setEmployeeID(orderRequest.getEmployeeID());
        order.setOrderDate(orderRequest.getOrderDate());
        order.setRequiredDate(orderRequest.getRequiredDate());
        order.setShippedDate(orderRequest.getShippedDate());
        order.setShipVia(orderRequest.getShipVia());
        order.setFreight(orderRequest.getFreight());
        order.setShipName(orderRequest.getShipName());
        order.setShipAddress(orderRequest.getShipAddress());
        order.setShipCity(orderRequest.getShipCity());
        order.setShipRegion(orderRequest.getShipRegion());
        order.setShipPostalCode(orderRequest.getShipPostalCode());
        order.setShipCountry(orderRequest.getShipCountry());
    }
}
