package smiteTroll.exceptions;

public class AccessingDatabaseException extends RuntimeException {

    public AccessingDatabaseException() {
        super();
    }

    public AccessingDatabaseException(String message){
        super(message);
    }
}
