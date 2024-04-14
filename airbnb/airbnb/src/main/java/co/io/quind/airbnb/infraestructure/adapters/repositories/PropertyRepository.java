package co.io.quind.airbnb.infraestructure.adapters.repositories;

import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.output.interfaces.IPropertyRepository;
import co.io.quind.airbnb.infraestructure.JPA.PropertyRepositoryJPA;
import co.io.quind.airbnb.infraestructure.entities.PropertyEntity;
import co.io.quind.airbnb.infraestructure.exception.DataBaseException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class PropertyRepository implements IPropertyRepository {

    private final PropertyRepositoryJPA propertyRepositoryJPA;

    public PropertyRepository(PropertyRepositoryJPA propertyRepositoryJPA) {
        this.propertyRepositoryJPA = propertyRepositoryJPA;
    }

    @Override
    public Property findById(Long id) {
        return null;
    }

    @Override
    public Property save(Property property) {
        {
            try
            {
                return PropertyEntity.toModel(propertyRepositoryJPA.save(PropertyEntity.toEntity(property)));
            }catch(Exception exception)
            {
                throw new RuntimeException("Ocurrió un error al intentar guardar la propiedad en la base de datos");
            }
        }
    }

    @Override
    public void deletePropertyById(Long id) {

    }

    @Override
    public List<Property> findAvailablePropertiesByPriceRange(double minPrice, double maxPrice) {
        try
        {
            return PropertyEntity.createToDomainList(propertyRepositoryJPA.findAvailablePropertiesByPriceRange(minPrice, maxPrice));
        }catch (Exception exception)
        {
            //log.error("Ocurrió un error al intentar consultar las propiedades", exception);
            throw new DataBaseException("Ocurrió un error al intentar consultar las pripiedades");
        }
    }

}
