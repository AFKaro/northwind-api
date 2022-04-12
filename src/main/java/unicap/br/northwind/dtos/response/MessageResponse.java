package unicap.br.northwind.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {

    private String message;

    private Object object;
}
