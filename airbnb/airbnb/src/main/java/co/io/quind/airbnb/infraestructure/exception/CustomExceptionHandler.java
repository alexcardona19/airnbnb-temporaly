package co.io.quind.airbnb.infraestructure.exception;

import co.io.quind.airbnb.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            log.warn("Campos con información incorrecta: Campo {} - Mensaje: {}", fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private String getTechnicalError(Exception ex) {
        if (ex instanceof DataAccessException) {
            if (ex.getCause() instanceof org.postgresql.util.PSQLException) {
                org.postgresql.util.PSQLException pgEx = (org.postgresql.util.PSQLException) ex.getCause();
                if ("42P01".equals(pgEx.getSQLState())) {
                    return "Error de base de datos: La tabla o relación especificada no existe.";
                }
            }
            return "Ocurrió un error de base de datos.";
        }
        return "Ocurrió un error técnico en la aplicación.";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ResponseEntity<Map<String, String>> notFoundException(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error: ", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataBaseException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> databaseError(Exception ex) {
        String technicalError = "Ocurrió un error técnico en la aplicación.";
        String userFriendlyError = "La aplicación experimentó un fallo, por favor intenta más tarde.";

        log.error("Error técnico no manejado", ex);

        Map<String, String> errorResponse = Map.of("technicalError", technicalError, "userFriendlyError", userFriendlyError);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
