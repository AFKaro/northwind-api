package unicap.br.northwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicap.br.northwind.domain.models.Order;
import unicap.br.northwind.domain.models.Product;
import unicap.br.northwind.exceptions.NotFoundException;
import unicap.br.northwind.repository.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Product getProduct(Integer idProduct) throws NotFoundException {
        Optional<Product> product = repository.findById(idProduct);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NotFoundException(Order.class.getSimpleName());
        }
    }


    public List<Product> getAllProduct() {
        return repository.findAll();
    }

}
