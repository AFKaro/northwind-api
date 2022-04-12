package unicap.br.northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicap.br.northwind.domain.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
