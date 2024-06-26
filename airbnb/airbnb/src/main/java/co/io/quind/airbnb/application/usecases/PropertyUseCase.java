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
    public PropertyDTO  registerProperty(PropertyDTO propertyDTO)
    {
        Property property = propertyDTO.toDomain();
        Property propertyCreated = propertyService.registerProperty(property);
        return PropertyDTO.fromDomain(propertyCreated);
    }

    public List<PropertyDTO> listProperties(double minPrice, double maxPrice) {
        return PropertyDTO.crateFromDomainList(propertyService.listProperties(minPrice,maxPrice));
    }

    public void deleteProperty(long id) {
        propertyService.deleteProperty(id);
    }

    public PropertyDTO rentProperty(long id){
        return PropertyDTO.fromDomain(propertyService.rentProperty(id));
    }

    public PropertyDTO editProperty(PropertyDTO propertyDTO){
        Property propertyToEdit = propertyDTO.toDomain();
        Property modifiedProperty = propertyService.editProperty(propertyToEdit);
        return PropertyDTO.fromDomain(modifiedProperty);
    }
}
