package unicap.br.northwind.domain.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeID;

    @Size(max = 10)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    @Size(max = 30)
    private String title;

    @Size(max = 25)
    private String titleOfCourtesy;

    private Date birthDate;

    private Date hireDate;

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
    private String homePhone;

}
