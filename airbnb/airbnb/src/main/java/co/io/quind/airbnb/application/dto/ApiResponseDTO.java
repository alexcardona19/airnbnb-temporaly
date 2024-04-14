package co.io.quind.airbnb.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    public int getStatus() {
        return status;
    }
}
