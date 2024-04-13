package co.io.quind.airbnb.application.exception;

public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
