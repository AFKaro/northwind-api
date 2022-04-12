package unicap.br.northwind.domain.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@IdClass(OrderDetailID.class)
@Table(name = "\"Order Details\"")
public class OrderDetail {

    @Id
    @Column(name = "OrderID")
    private Integer orderID;

    @Id
    @Column(name = "ProductID")
    private Integer productID;

    @Column(name = "UnitPrice")
    private Double unitPrice;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Discount")
    private Double discount;
}
