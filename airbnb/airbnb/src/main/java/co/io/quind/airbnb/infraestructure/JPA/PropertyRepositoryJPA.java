package co.io.quind.airbnb.infraestructure.JPA;

import co.io.quind.airbnb.infraestructure.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyRepositoryJPA extends JpaRepository<PropertyEntity, Long> {
    boolean existsByName(String name);
    @Query("SELECT p FROM PropertyEntity p WHERE p.isAvailable = true AND isDeleted = false AND p.price BETWEEN :minPrice AND :maxPrice")
    List<PropertyEntity> findAvailablePropertiesByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
