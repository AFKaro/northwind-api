package unicap.br.northwind.dtos.response;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class OrdersAndCustomersReportResponse {

    private String customerID;

    private String customerName;

    private Integer quantity_orders;

    private Integer quantity_products;

    private BigDecimal amountSpent;

}
