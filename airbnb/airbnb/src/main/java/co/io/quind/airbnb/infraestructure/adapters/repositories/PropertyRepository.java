package co.io.quind.airbnb.infraestructure.adapters.repositories;

import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.output.interfaces.IPropertyRepository;
import co.io.quind.airbnb.infraestructure.JPA.PropertyRepositoryJPA;
import co.io.quind.airbnb.infraestructure.Mapper.PropertyMapper;
import co.io.quind.airbnb.infraestructure.entities.PropertyEntity;
import org.springframework.stereotype.Repository;

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
                return PropertyMapper.toModel(propertyRepositoryJPA.save(PropertyMapper.toEntity(property)));
            }catch(Exception exception)
            {
                throw new RuntimeException("Ocurrió un error al intentar guardar la propiedad en la base de datos");
            }
        }
    }

}
