package co.io.quind.airbnb.application.mapper;

import co.io.quind.airbnb.application.dto.PropertyDTO;
import co.io.quind.airbnb.domain.models.Property;

public class PropertyMapper {
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
}
