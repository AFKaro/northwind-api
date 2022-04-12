package unicap.br.northwind.control;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicap.br.northwind.domain.models.Customer;
import unicap.br.northwind.domain.enums.MessageEnum;
import unicap.br.northwind.dtos.request.CustomerRequest;
import unicap.br.northwind.dtos.response.ErrorResponse;
import unicap.br.northwind.dtos.response.MessageResponse;
import unicap.br.northwind.dtos.response.OrdersAndCustomersReportResponse;
import unicap.br.northwind.service.CustomerService;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRequest customerRequest) {

        try {
            Customer customer = service.registerCustomer(customerRequest);
            return ResponseEntity.ok(new MessageResponse(MessageEnum.REGISTERED.getMessage(), customer));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{idCustomer}")
    public ResponseEntity<?> editCustomer(@PathVariable("idCustomer") String idCustomer,
                                          @RequestBody CustomerRequest customerRequest) {
        try {
            Customer customer = service.editCustomer(idCustomer, customerRequest);
            return ResponseEntity.ok(new MessageResponse(MessageEnum.EDITED.getMessage(), customer));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idCustomer}")
    public ResponseEntity<?> getCustomer(@PathVariable("idCustomer") String idCustomer) {

        try {
            Customer customer = service.getCustomer(idCustomer);
            return ResponseEntity.ok(new MessageResponse(MessageEnum.EDITED.getMessage(), customer));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public ResponseEntity<?> getCustomers() {

        try {
            List<Customer> customers = service.getAllCustomers();
            return ResponseEntity.ok(new MessageResponse(MessageEnum.FOUND.getMessage(), customers));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/report")
    public ResponseEntity<?> getOrdersAndCustomersReport() {

        try {
            List<OrdersAndCustomersReportResponse> report = service.getOrdersAndCustomersReport();
            return ResponseEntity.ok(new MessageResponse(MessageEnum.FOUND.getMessage(), report));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{idCustomer}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("idCustomer") String idCustomer) {

        try {
            Customer customer = service.deleteCustomer(idCustomer);
            return ResponseEntity.ok(new MessageResponse(MessageEnum.DELETED.getMessage(), customer));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }
}
