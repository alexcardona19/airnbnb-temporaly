package co.io.quind.airbnb.domain.ports.in.service;
import co.io.quind.airbnb.application.usecases.bussinessRules.BusinessRulesValidator;
import co.io.quind.airbnb.domain.exception.BusinessException;
import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.in.interfaces.IPropertyService;
import co.io.quind.airbnb.infraestructure.adapters.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService implements IPropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<Property> listProperties(double minPrice, double maxPrice) {
        return propertyRepository.findAvailablePropertiesByPriceRange(minPrice,maxPrice);
    }

    @Override
    public Property registerProperty(Property property) {
        if(propertyRepository.existsByName(property.getName()))
        {
            BusinessRulesValidator.validateDuplicateName(true);
        }
        BusinessRulesValidator.validateProperty(property);
        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id);
        if (property == null) {
            throw new BusinessException("La propiedad con el ID especificado no ha sido encontrada");
        } else if (BusinessRulesValidator.validateCreationDate(property.getDate())) {
            property.setDeleted(true);
            propertyRepository.save(property);
        } else {
            throw new BusinessException("La propiedad no puede ser borrada porque tiene una vigencia de creación superior a un mes.");
        }
    }

    @Override
    public Property rentProperty(Long id) throws BusinessException {
        Property property = propertyRepository.findById(id);
        if (!property.isAvailable()) {
            throw new BusinessException("La propiedad ya está arrendada");
        }
        property.setAvailable(false);

        return propertyRepository.save(property);
    }
}
