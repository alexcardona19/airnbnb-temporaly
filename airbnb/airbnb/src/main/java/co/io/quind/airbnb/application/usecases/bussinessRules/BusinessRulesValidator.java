package co.io.quind.airbnb.application.usecases.bussinessRules;

import co.io.quind.airbnb.application.exception.BusinessException;
import co.io.quind.airbnb.domain.models.Property;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BusinessRulesValidator {
     private static final String DUPLICATE_NAME_ERROR = "No se puede registrar una propiedad con el mismo nombre";
     private static final List<String> VALID_LOCATIONS = Arrays.asList("Medellin", "Bogota", "Cali", "Cartagena");
     private static final String INVALID_LOCATION_ERROR = "La ubicación de la propiedad no es válida";

     public static void validateProperty(Property property){
          if (!validateLocation(property.getLocation())) {
               throw new BusinessException(INVALID_LOCATION_ERROR);
          }
     }
     private static boolean validateLocation(String location) {
          return VALID_LOCATIONS.contains(location);
     }

}
