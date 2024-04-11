package co.io.quind.airbnb.domain.models;
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
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private Long id;
    private String name;
    private String location;
    private String image;
    private boolean isAvailable;
    private BigDecimal price;
    private Date date;
}

