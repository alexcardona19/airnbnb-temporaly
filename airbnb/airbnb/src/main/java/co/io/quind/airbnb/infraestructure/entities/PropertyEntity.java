package co.io.quind.airbnb.infraestructure.entities;

import co.io.quind.airbnb.domain.models.Property;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "properties")
public class PropertyEntity {

    public PropertyEntity() {
    }

    public PropertyEntity(Long id, String name, String location, String image, boolean isAvailable, BigDecimal price, Date date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.image = image;
        this.isAvailable = isAvailable;
        this.price = price;
        this.date = date;
    }

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
    private BigDecimal price;

    @Column(name = "dateofcreation")
    private Date date;

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
