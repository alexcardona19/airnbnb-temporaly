package co.io.quind.airbnb.application.dto;

import co.io.quind.airbnb.domain.models.Property;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PropertyDTO {
    @NotEmpty(message = "id may not be empty")
    private Long id;

    @NotEmpty(message = "name may not be empty")
    private String name;

    @NotEmpty(message = "location may not be empty")
    private String location;

    @NotEmpty(message = "image may not be empty")
    private String image;

    @NotEmpty(message = "available may not be empty")
    private boolean isAvailable;

    @NotEmpty(message = "Price may not be empty")
    @Min(value = 1, message = "El precio debe ser mayor que cero")
    private BigDecimal price;

    private Date date;
}
