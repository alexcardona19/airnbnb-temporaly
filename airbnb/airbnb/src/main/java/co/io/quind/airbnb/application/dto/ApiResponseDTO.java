package co.io.quind.airbnb.application.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponseDTO {
    private String message;
    private int status;
    private Object data;

    public ApiResponseDTO(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();
    }
}
