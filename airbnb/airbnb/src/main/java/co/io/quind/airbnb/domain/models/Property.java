package co.io.quind.airbnb.domain.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
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
    private boolean isDeleted = false;
}
