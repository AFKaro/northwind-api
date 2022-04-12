package unicap.br.northwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicap.br.northwind.domain.models.Customer;
import unicap.br.northwind.domain.models.Order;
import unicap.br.northwind.domain.models.OrderDetail;
import unicap.br.northwind.dtos.mapper.OrderMapper;
import unicap.br.northwind.dtos.request.CustomerRequest;
import unicap.br.northwind.dtos.request.OrderDetailRequest;
import unicap.br.northwind.dtos.request.OrderRequest;
import unicap.br.northwind.dtos.response.ErrorResponse;
import unicap.br.northwind.exceptions.InvalidFieldException;
import unicap.br.northwind.exceptions.NotFoundException;
import unicap.br.northwind.exceptions.NotSavedException;
import unicap.br.northwind.repository.OrderRepository;
import unicap.br.northwind.utils.ModelUtil;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Transactional
    public Order registerOrder(OrderRequest orderRequest) throws
            NotSavedException {
        OrderMapper mapper = new OrderMapper();
        try {
            Order order = new Order();

            mapper.map(orderRequest, order);

            repository.save(order);

            orderRequest.getOrderDetails().forEach(orderDetail ->
                    orderDetailService.registerOrderDetails(orderDetail,
                            order.getOrderID()));

            return order;
        } catch (Exception e) {
            throw new NotSavedException();
        }
    }


    public Order getOrder(Integer idOrder) throws NotFoundException {
        Optional<Order> order = repository.findById(idOrder);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new NotFoundException(Order.class.getSimpleName());
        }
    }


    public List<Order> getAllOrder() {
        return repository.findAll();
    }

}
