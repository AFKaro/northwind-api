package unicap.br.northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicap.br.northwind.domain.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
