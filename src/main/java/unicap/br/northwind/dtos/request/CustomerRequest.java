package unicap.br.northwind.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {

    private String companyName;

    private String contactTitle;

    private String contactName;

    private String address;

    private String city;

    private String region;

    private String postalCode;

    private String country;

    private String phone;

    private String fax;

}
