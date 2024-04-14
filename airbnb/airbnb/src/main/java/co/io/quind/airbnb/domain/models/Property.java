package co.io.quind.airbnb.domain.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private Long id;
    private String name;
    private String location;
    private String image;
    private boolean isAvailable;
    private double price;
    private Date date;
    private boolean isDeleted;
}
