package co.io.quind.airbnb.domain.ports.output.interfaces;

import co.io.quind.airbnb.domain.models.Property;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IPropertyRepository {
    Property findById(Long id);
    Property save(Property property);
    void deleteById(Long id);
    boolean existsByName(String name);
    Property edit (Property property);
    List<Property> findAvailablePropertiesByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}
