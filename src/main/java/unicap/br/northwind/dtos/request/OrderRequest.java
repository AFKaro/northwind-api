package unicap.br.northwind.dtos.request;

import lombok.Builder;
import lombok.Data;
import unicap.br.northwind.domain.models.OrderDetail;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderRequest {

    private String customerID;

    private Integer employeeID;

    private List<OrderDetailRequest> orderDetails;

    private Date orderDate;

    private Date requiredDate;

    private Date shippedDate;

    private Integer shipVia;

    private Double freight;

    private String shipName;

    private String shipAddress;

    private String shipCity;

    private String shipRegion;

    private String shipPostalCode;

    private String shipCountry;

}
