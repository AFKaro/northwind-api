package unicap.br.northwind.domain.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Shippers")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperID;

    @Size(max = 40)
    private String companyName;

    @Size(max = 24)
    private String phone;
}
