package co.io.quind.airbnb.infraestructure.entities;

import co.io.quind.airbnb.domain.models.Property;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "properties")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "image")
    private String image;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "price")
    private double price;

    @Column(name = "dateofcreation")
    private Date date;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;


    public static Property toModel(PropertyEntity propertyEntity) {
        return new Property(
                propertyEntity.getId(),
                propertyEntity.getName(),
                propertyEntity.getLocation(),
                propertyEntity.getImage(),
                propertyEntity.isAvailable(),
                propertyEntity.getPrice(),
                propertyEntity.getDate(),
                propertyEntity.isDeleted()
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
                property.getDate(),
                property.isDeleted()
        );
    }

    public static List<PropertyEntity> createFromDomainList(List<Property> list)
    {
        return list.stream()
                .map(PropertyEntity::toEntity)
                .toList();
    }

    public static List<Property> createToDomainList(List<PropertyEntity> list)
    {
        return list.stream()
                .map(PropertyEntity::toModel)
                .toList();
    }
}
