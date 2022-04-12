package unicap.br.northwind.control;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicap.br.northwind.domain.enums.MessageEnum;
import unicap.br.northwind.domain.models.Order;
import unicap.br.northwind.domain.models.OrderDetail;
import unicap.br.northwind.domain.models.Product;
import unicap.br.northwind.dtos.request.OrderRequest;
import unicap.br.northwind.dtos.response.ErrorResponse;
import unicap.br.northwind.dtos.response.MessageResponse;
import unicap.br.northwind.service.OrderDetailService;
import unicap.br.northwind.service.OrderService;
import unicap.br.northwind.service.ProductService;

import java.util.List;
import java.util.Objects;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getProduct(@PathVariable("idProduct") Integer idProduct) {

        try {
            Product product = service.getProduct(idProduct);
            return ResponseEntity.ok(new MessageResponse(MessageEnum.FOUND.getMessage(), product));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public ResponseEntity<?> getProduct() {

        try {
            List<Product> products = service.getAllProduct();
            return ResponseEntity.ok(new MessageResponse(MessageEnum.FOUND.getMessage(), products));
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse(e.getMessage()));
        }
    }

}
