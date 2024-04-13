package co.io.quind.airbnb.application.dto;
import co.io.quind.airbnb.domain.models.Property;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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

    public PropertyDTO() {

    }

    public PropertyDTO(Long id, String name, String location, String image, boolean isAvailable, BigDecimal price, Date date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.image = image;
        this.isAvailable = isAvailable;
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Property toDomain(PropertyDTO propertyDTO) {
        return new Property(
                propertyDTO.getId(),
                propertyDTO.getName(),
                propertyDTO.getLocation(),
                propertyDTO.getImage(),
                propertyDTO.isAvailable(),
                propertyDTO.getPrice(),
                propertyDTO.getDate()
        );
    }

    public static PropertyDTO fromDomain(Property property) {
        return new PropertyDTO(
                property.getId(),
                property.getName(),
                property.getLocation(),
                property.getImage(),
                property.isAvailable(),
                property.getPrice(),
                property.getDate()
        );
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
