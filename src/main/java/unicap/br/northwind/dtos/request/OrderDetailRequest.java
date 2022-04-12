package unicap.br.northwind.dtos.request;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderDetailRequest {

    private Integer productID;

    private Double unitPrice;

    private Integer quantity;

    private Double discount;

}
