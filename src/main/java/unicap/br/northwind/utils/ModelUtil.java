package unicap.br.northwind.utils;

import org.modelmapper.ModelMapper;
import unicap.br.northwind.dtos.response.ErrorResponse;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;


public class ModelUtil {

    private static ModelUtil instance;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final ModelMapper mapper = new ModelMapper();

    public static synchronized ModelUtil getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ModelUtil();
        }
        return instance;
    }


    public void map(Object source, Object destination) {
        mapper.map(source, destination);
    }


    public ErrorResponse validate(Object obj) {
        List<ErrorResponse> errors = validator.validate(obj).stream()
                .map(r -> new ErrorResponse(r.getMessage()))
                .toList();
        if(!errors.isEmpty()) return errors.get(0);
        else return null;
    }
}
