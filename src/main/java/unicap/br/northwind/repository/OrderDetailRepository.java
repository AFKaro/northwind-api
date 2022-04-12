package unicap.br.northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unicap.br.northwind.domain.models.OrderDetail;
import unicap.br.northwind.domain.models.OrderDetailID;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailID> {

    @Query("select od from OrderDetail od where od.orderID =:orderid")
    List<OrderDetail> getOrderDetailByOrderID(@Param("orderid") Integer orderid);
}
