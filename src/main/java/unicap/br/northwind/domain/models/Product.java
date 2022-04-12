package unicap.br.northwind.domain.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productID;

    @Size(max = 40)
    private String productName;

    private Integer supplierID;

    private Integer categoryID;

    @Size(max = 15)
    private String quantityPerUnit;

    private Double unitPrice;

    private Integer unitsInStock;

    private Integer unitsOnOrder;

    private Integer reorderLevel;

    private Byte discontinued;
}
