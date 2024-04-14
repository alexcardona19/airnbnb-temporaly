package co.io.quind.airbnb.application.usecases;

import co.io.quind.airbnb.application.dto.PropertyDTO;
import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.in.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyUseCase {
    private final PropertyService propertyService;

    @Autowired
    public PropertyUseCase(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    public Property registerProperty(PropertyDTO propertyDTO)
    {
        return propertyService.registerProperty(PropertyDTO.toDomain(propertyDTO));
    }

    public List<PropertyDTO> listProperties(double minPrice, double maxPrice) {
        return PropertyDTO.crateFromDomainList(propertyService.listProperties(minPrice,maxPrice));
    }

    }
