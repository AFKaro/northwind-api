package unicap.br.northwind.domain.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "Suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierID;

    @Size(max = 40)
    private String companyName;

    @Size(max = 30)
    private String contactName;

    @Size(max = 30)
    private String contactTitle;

    @Size(max = 60)
    private String address;

    @Size(max = 15)
    private String city;

    @Size(max = 15)
    private String region;

    @Size(max = 10)
    private String postalCode;

    @Size(max = 15)
    private String country;

    @Size(max = 24)
    private String phone;

    @Size(max = 24)
    private String fax;

    private String homePage;
}
