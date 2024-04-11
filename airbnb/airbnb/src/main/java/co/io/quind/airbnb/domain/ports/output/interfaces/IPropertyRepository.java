package co.io.quind.airbnb.domain.ports.output.interfaces;

import co.io.quind.airbnb.domain.models.Property;
import org.springframework.stereotype.Repository;

@Repository
public interface IPropertyRepository {
    Property findById(Long id);
    Property save(Property property);
}
