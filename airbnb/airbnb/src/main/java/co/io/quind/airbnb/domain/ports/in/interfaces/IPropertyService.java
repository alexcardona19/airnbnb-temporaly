package co.io.quind.airbnb.domain.ports.in.interfaces;

import co.io.quind.airbnb.domain.models.Property;

import java.math.BigDecimal;
import java.util.List;

public interface IPropertyService {
   List<Property> listProperties(BigDecimal minPrice, BigDecimal maxPrice);
   Property registerProperty(Property property);
   void deleteProperty(Long PropertyId);
   void rentProperty(Long PropertyId, Property property);
}
