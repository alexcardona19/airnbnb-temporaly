package co.io.quind.airbnb.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    private Long id;

    @NotEmpty(message = "name may not be empty")
    private String name;

    @NotEmpty(message = "location may not be empty")
    private String location;

    @NotEmpty(message = "image may not be empty")
    private String image;

    private boolean isAvailable;

    @Min(value = 1, message = "El precio debe ser mayor que cero")
    private BigDecimal price;

    private Date date;
}
