package unicap.br.northwind.dtos.mapper;

import unicap.br.northwind.domain.models.OrderDetail;
import unicap.br.northwind.dtos.request.OrderDetailRequest;


public class OrderDetailMapper {

    public void map(OrderDetailRequest orderDetailRequest, OrderDetail orderDetail) {
        orderDetail.setProductID(orderDetailRequest.getProductID());
        orderDetail.setUnitPrice(orderDetailRequest.getUnitPrice());
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setDiscount(orderDetailRequest.getDiscount());
    }
}
