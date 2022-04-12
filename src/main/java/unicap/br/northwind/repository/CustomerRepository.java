package unicap.br.northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unicap.br.northwind.domain.models.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "exec dbo.resumoComprasEClientes", nativeQuery = true)
    List<Object[]> getResumOrdersAndClients();
}
