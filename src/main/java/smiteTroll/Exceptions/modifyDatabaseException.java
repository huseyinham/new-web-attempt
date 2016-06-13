package smiteTroll.exceptions;

public class ModifyDatabaseException extends RuntimeException {

    public ModifyDatabaseException() {
        super();
    }

    public ModifyDatabaseException(String message){
        super(message);
    }
}
