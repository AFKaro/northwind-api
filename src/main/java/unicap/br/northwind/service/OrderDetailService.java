package unicap.br.northwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicap.br.northwind.domain.models.Order;
import unicap.br.northwind.domain.models.OrderDetail;
import unicap.br.northwind.dtos.mapper.OrderDetailMapper;
import unicap.br.northwind.dtos.request.OrderDetailRequest;
import unicap.br.northwind.repository.OrderDetailRepository;
import unicap.br.northwind.repository.OrderRepository;
import unicap.br.northwind.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository repository;


    @Transactional
    public OrderDetail registerOrderDetails(OrderDetailRequest orderDetailRequest, Integer orderID) {
        OrderDetailMapper mapper = new OrderDetailMapper();

        OrderDetail orderDetail = new OrderDetail();
        mapper.map(orderDetailRequest, orderDetail);

        orderDetail.setOrderID(orderID);

        repository.save(orderDetail);

        return orderDetail;
    }

    public List<OrderDetail> getAll() {
        return repository.findAll();
    }

    public List<OrderDetail> getAllOrderDetailsByOrderID(Integer orderid) {
        return repository.getOrderDetailByOrderID(orderid);
    }

}
