package unicap.br.northwind.domain.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name= "Customers")
public class Customer {

    @Id
    @Size(max = 5)
    @Column(name = "CustomerID")
    private String customerID;

    @Size(max = 40)
    @Column(name = "CompanyName")
    private String companyName;

    @Size(max = 30)
    @Column(name = "ContactName")
    private String contactName;

    @Size(max = 30)
    @Column(name = "ContactTitle")
    private String contactTitle;

    @Size(max = 60)
    @Column(name = "Address")
    private String address;

    @Size(max = 15)
    @Column(name = "City")
    private String city;

    @Size(max = 15)
    @Column(name = "Region")
    private String region;

    @Size(max = 10)
    @Column(name = "PostalCode")
    private String postalCode;

    @Size(max = 15)
    @Column(name = "Country")
    private String country;

    @Size(max = 24)
    @Column(name = "Phone")
    private String phone;

    @Size(max = 24)
    @Column(name = "Fax")
    private String fax;
}
