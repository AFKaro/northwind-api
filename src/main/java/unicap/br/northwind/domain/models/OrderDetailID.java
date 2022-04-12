package unicap.br.northwind.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailID implements Serializable {

    private Integer orderID;

    private Integer productID;

}
