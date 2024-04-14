package co.io.quind.airbnb.application.dto;

import co.io.quind.airbnb.domain.models.Property;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private double price;

    private Date date;

    private boolean isDeleted;

    public Property toDomain() {
        return Property.builder()
                .id(this.getId())
                .name(this.getName())
                .location(this.getLocation())
                .image(this.getImage())
                .isAvailable(this.isAvailable())
                .price(this.getPrice())
                .date(this.getDate())
                .isDeleted(this.isDeleted())
                .build();
    }

    public static PropertyDTO fromDomain(Property property)
    {
        return PropertyDTO.builder()
                .id(property.getId())
                .name(property.getName())
                .location(property.getLocation())
                .image(property.getImage())
                .isAvailable(property.isAvailable())
                .price(property.getPrice())
                .isDeleted(property.isDeleted())
                .date(property.getDate())
                .build();
    }
    public static List<PropertyDTO> crateFromDomainList(List<Property> list)
    {
        return list.stream()
                .map(PropertyDTO::fromDomain)
                .toList();
    }

    public static List<Property> createToDomainList(List<PropertyDTO> list)
    {
        return list.stream()
                .map(PropertyDTO::toDomain)
                .toList();
    }
}
