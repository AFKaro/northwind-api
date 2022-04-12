package unicap.br.northwind.exceptions;

public class InvalidFieldException extends Exception {
    private static final String message = "Invalid field: %s ";

    public InvalidFieldException(String field) {
        super(String.format(message, field));
    }
}