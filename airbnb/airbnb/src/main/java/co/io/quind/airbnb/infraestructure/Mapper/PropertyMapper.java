package co.io.quind.airbnb.infraestructure.Mapper;

import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.infraestructure.entities.PropertyEntity;

public class PropertyMapper {
        public static Property toModel(PropertyEntity propertyEntity) {
            return new Property(
                    propertyEntity.getId(),
                    propertyEntity.getName(),
                    propertyEntity.getLocation(),
                    propertyEntity.getImage(),
                    propertyEntity.isAvailable(),
                    propertyEntity.getPrice(),
                    propertyEntity.getDate()
            );
        }

        public static PropertyEntity toEntity(Property property) {
            return new PropertyEntity (
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
