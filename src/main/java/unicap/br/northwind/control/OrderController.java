package unicap.br.northwind.control;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicap.br.northwind.domain.enums.MessageEnum;
import unicap.br.northwind.domain.models.Order;
import unicap.br.northwind.domain.models.OrderDetail;
import unicap.br.northwind.dtos.request.OrderDetailRequest;
import unicap.br.northwind.dtos.request.OrderRequest;
import unicap.br.northwind.dtos.response.ErrorResponse;
import unicap.br.northwind.dtos.response.MessageResponse;
import unicap.br.northwind.service.OrderDetailService;
import unicap.br.northwind.service.OrderService;

import java.util.List;
import java.util.Objects;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    private final OrderDetailService orderDetailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<?> registerOrder(@RequestBody OrderRequest orderRequest) {

        try {
            Order order = service.registerOrder(orderRequest);
            return ResponseEntity.ok(new MessageResponse(MessageEnum.REGISTERED.getMessage(), order));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idOrder}")
    public ResponseEntity<?> getOrder(@PathVariable("idOrder") Integer idOrder) {

        try {
            Order order = service.getOrder(idOrder);
            return ResponseEntity.ok(new MessageResponse(MessageEnum.FOUND.getMessage(), order));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public ResponseEntity<?> getOrders() {

        try {
            List<Order> orders = service.getAllOrder();
            return ResponseEntity.ok(new MessageResponse(MessageEnum.FOUND.getMessage(), orders));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all/details")
    public ResponseEntity<?> getOrderDetailsByOrderID(
            @RequestParam(name = "orderID", required = false) Integer orderID) {

        try {
            List<OrderDetail> orderDetails;

            if (Objects.isNull(orderID)) {
                orderDetails = orderDetailService.getAll();
            } else {
                orderDetails = orderDetailService.getAllOrderDetailsByOrderID(orderID);
            }

            return ResponseEntity.ok(new MessageResponse(MessageEnum.FOUND.getMessage(), orderDetails));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }

}
