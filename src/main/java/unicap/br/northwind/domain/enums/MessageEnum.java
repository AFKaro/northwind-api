package unicap.br.northwind.domain.enums;

public enum MessageEnum {

    REGISTERED("Registered successfully!"),
    EDITED("Successfully edited!"),
    FOUND("Found!"),
    NOT_FOUND("Not found!"),
    NOT_REGISTERED("Not registered!"),
    EMPTY("Is empty!"),
    DELETED("Successfully deleted!"),
    ALREADY_USED("Is already being used!"),
    ADDED("Successfully added");

    String message;

    MessageEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}