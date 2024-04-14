package co.io.quind.airbnb.domain.ports.output.interfaces;

import co.io.quind.airbnb.domain.models.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IPropertyRepository {
    Property findById(Long id);
    Property save(Property property);
    void deleteById(Long id);
    @Query("SELECT p FROM properties p WHERE p.isAvailable = true AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Property> findAvailablePropertiesByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}
