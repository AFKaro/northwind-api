package unicap.br.northwind.exceptions;

public class NotFoundException extends Exception {
    private static final String message = "Not Found %s";

    public NotFoundException(String object) {
        super(String.format(message, object));
    }
}