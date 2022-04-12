package unicap.br.northwind.domain.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    private String customerID;

    private Integer employeeID;

    private Date orderDate;

    private Date requiredDate;

    private Date shippedDate;

    private Integer shipVia;

    private Double freight;

    @Size(max = 40)
    private String shipName;

    @Size(max = 60)
    private String shipAddress;

    @Size(max = 15)
    private String shipCity;

    @Size(max = 15)
    private String shipRegion;

    @Size(max = 10)
    private String shipPostalCode;

    @Size(max = 15)
    private String shipCountry;
}
