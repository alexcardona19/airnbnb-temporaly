package co.io.quind.airbnb.domain.ports.in.interfaces;

import co.io.quind.airbnb.domain.models.Property;

import java.util.List;

public interface IPropertyService {
   List<Property> listProperties(double minPrice, double maxPrice);
   Property registerProperty(Property property);
   void deleteProperty(Long id);
   Property rentProperty(Long id);
   Property editProperty(Property property);

}
