package co.io.quind.airbnb.application.usecases.bussinessRules;

import co.io.quind.airbnb.domain.exception.BusinessException;
import co.io.quind.airbnb.domain.models.Property;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BusinessRulesValidator {
    private static final String DUPLICATE_NAME_ERROR = "No se puede registrar una propiedad con el mismo nombre";
     private static final List<String> VALID_LOCATIONS = Arrays.asList("Medellin", "Bogota", "Cali", "Cartagena");
     private static final String INVALID_LOCATION_ERROR = "La ubicación de la propiedad no es válida";
    private static final String INVALID_PRICE_ERROR = "El precio de la propiedad no cumple con los requisitos";
    private static final String INVALID_CREATION_DATE_ERROR = "La propiedad no puede ser borrada porque tiene una vigencia de creación superior a un mes.";

     public static void validateProperty(Property property){
         if (!validateLocation(property.getLocation())) {
               throw new BusinessException(INVALID_LOCATION_ERROR);
         }

         if (("Bogota".equals(property.getLocation()) || "Cali".equals(property.getLocation())) && property.getPrice() <= 2000000) {
             throw new BusinessException(INVALID_PRICE_ERROR);
         }
     }
    public static boolean validateCreationDate(Date creationDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date thirtyDaysAgo = calendar.getTime();
        return creationDate.after(thirtyDaysAgo);
    }
    private static boolean validateLocation(String location) {
        return VALID_LOCATIONS.contains(location);
    }
}
