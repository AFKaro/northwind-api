package unicap.br.northwind.domain.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryID;

    @Size(max = 15)
    private String categoryName;

    private String description;
}
