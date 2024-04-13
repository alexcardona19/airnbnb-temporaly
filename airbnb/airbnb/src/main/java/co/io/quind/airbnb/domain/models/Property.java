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

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}
