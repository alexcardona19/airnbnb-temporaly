package co.io.quind.airbnb.infraestructure.JPA;

import co.io.quind.airbnb.infraestructure.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepositoryJPA extends JpaRepository<PropertyEntity, Long> {
}
