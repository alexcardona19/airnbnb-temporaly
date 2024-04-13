package co.io.quind.airbnb.domain.models;
import java.math.BigDecimal;
import java.util.Date;

public class Property {
    private Long id;
    private String name;
    private String location;
    private String image;
    private boolean isAvailable;
    private BigDecimal price;
    private Date date;

    public Property() {

    }

    public Property(Long id, String name, String location, String image, boolean isAvailable, BigDecimal price, Date date) {
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
}
