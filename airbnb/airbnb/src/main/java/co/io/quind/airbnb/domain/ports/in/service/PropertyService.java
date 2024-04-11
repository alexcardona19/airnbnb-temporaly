package co.io.quind.airbnb.domain.ports.in.service;
import co.io.quind.airbnb.application.usecases.bussinessRules.BusinessRulesValidator;
import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.in.interfaces.IPropertyService;
import co.io.quind.airbnb.infraestructure.adapters.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PropertyService implements IPropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    @Override
    public List<Property> listProperties(BigDecimal minPrice, BigDecimal maxPrice) {
        return null;
    }

    @Override
    public Property registerProperty(Property property) {
        BusinessRulesValidator.validateProperty(property);
        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long PropertyId) {

    }

    @Override
    public void rentProperty(Long PropertyId, Property property) {

    }
}
