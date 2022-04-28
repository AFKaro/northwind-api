package unicap.br.northwind.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicap.br.northwind.domain.models.Order;
import unicap.br.northwind.dtos.mapper.OrderMapper;
import unicap.br.northwind.dtos.request.OrderRequest;
import unicap.br.northwind.exceptions.NotFoundException;
import unicap.br.northwind.exceptions.NotSavedException;
import unicap.br.northwind.repository.OrderRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Transactional
    public Order registerOrder(OrderRequest orderRequest) throws
            NotSavedException, SQLException {
        OrderMapper mapper = new OrderMapper();
        try {
            Order order = new Order();

            mapper.map(orderRequest, order);

            if (Objects.isNull(order.getOrderDate())){
                order.setOrderDate(new Date());
                order.setShippedDate(new Date());
            }
            repository.save(order);

            orderRequest.getOrderDetails().forEach(orderDetail ->
                    orderDetailService.registerOrderDetails(orderDetail,
                            order.getOrderID()));

            return order;
        } catch (SQLGrammarException e) {
            throw e.getSQLException();
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
