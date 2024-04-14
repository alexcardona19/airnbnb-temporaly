package co.io.quind.airbnb.infraestructure.adapters.repositories;

import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.output.interfaces.IPropertyRepository;
import co.io.quind.airbnb.infraestructure.JPA.PropertyRepositoryJPA;
import co.io.quind.airbnb.infraestructure.entities.PropertyEntity;
import co.io.quind.airbnb.infraestructure.exception.DataBaseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class PropertyRepository implements IPropertyRepository {

    private final PropertyRepositoryJPA propertyRepositoryJPA;
    @Override
    public Property findById(Long id) {
        try
        {
            Optional<PropertyEntity> propertyOptional = propertyRepositoryJPA.findById(id);
            return propertyOptional.map(PropertyEntity::toModel).orElse(null);
        }catch(Exception exception)
        {
            log.error("Ocurrió un error al intentar encontrar la propiedad por id de la base de datos", exception);
            throw new DataBaseException("Ocurrió un error al intentar encontrar la propiedad por id de la BD");
        }
    }

    @Override
    public Property save(Property property) {
        {
            try
            {
                return PropertyEntity.toModel(propertyRepositoryJPA.save(PropertyEntity.toEntity(property)));
            }catch(Exception exception)
            {
                log.error("Ocurrió un error al intentar guardar la propiedad en la base de datos", exception);
                throw new DataBaseException("Ocurrió un error al intentar guardar la propiedad en la base de datos");
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        propertyRepositoryJPA.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return propertyRepositoryJPA.existsByName(name);
    }

    @Override
    public List<Property> findAvailablePropertiesByPriceRange(double minPrice, double maxPrice) {
        try
        {
            return PropertyEntity.createToDomainList(propertyRepositoryJPA.findAvailablePropertiesByPriceRange(minPrice, maxPrice));
        }catch (Exception exception)
        {
            log.error("Ocurrió un error al intentar consultar las propiedades disponibles", exception);
            throw new DataBaseException("Ocurrió un error al intentar consultar las pripiedades");
        }
    }

}
