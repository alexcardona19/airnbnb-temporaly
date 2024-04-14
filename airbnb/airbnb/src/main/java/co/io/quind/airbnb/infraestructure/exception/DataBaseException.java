package co.io.quind.airbnb.infraestructure.exception;

public class DataBaseException extends RuntimeException {

    public DataBaseException(){
    }
    public DataBaseException(String message) {
        super(message);
    }
}
