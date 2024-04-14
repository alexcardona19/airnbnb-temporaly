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

    private static final String EMPTY_FIELD_ERROR = "El campo %s no puede estar vacío";


    public static void validateProperty(Property property){
         if (!validateLocation(property.getLocation())) {
               throw new BusinessException(INVALID_LOCATION_ERROR);
         }

         if (("Bogota".equals(property.getLocation()) || "Cali".equals(property.getLocation())) && property.getPrice() <= 2000000) {
             throw new BusinessException(INVALID_PRICE_ERROR);
         }

        validateNotEmpty(property.getName(), "name");
        validateNotEmpty(property.getLocation(), "location");
        validateNotEmpty(property.isAvailable(), "isAvailable");
        validateNotEmpty(property.getPrice(), "price");
        validateNotEmpty(property.getImage(), "image");
        validateNotEmpty(property.getDate(), "date");
     }

    public static void validateDuplicateName(boolean exists) {
        if (exists) {
            throw new BusinessException(DUPLICATE_NAME_ERROR);
        }
    }

    private static void validateNotEmpty(Object value, String fieldName) {
        if (value == null || (value instanceof String && ((String) value).isEmpty())) {
            throw new BusinessException(String.format(EMPTY_FIELD_ERROR, fieldName));
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
