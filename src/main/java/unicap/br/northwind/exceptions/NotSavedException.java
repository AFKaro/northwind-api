package unicap.br.northwind.exceptions;

public class NotSavedException extends Exception {
    private static final String message = "Not Saved Object!";

    public NotSavedException() {
        super(message);
    }
}