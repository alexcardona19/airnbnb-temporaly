package co.io.quind.airbnb.domain.ports.output;

import co.io.quind.airbnb.domain.models.Property;

public interface IPropertyRepository {
    Property findById(Long id);
    void save(Property property);
}
