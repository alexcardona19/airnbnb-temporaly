package co.io.quind.airbnb.application;

import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.in.service.PropertyService;
import co.io.quind.airbnb.infraestructure.adapters.repositories.PropertyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

 class PropertyUseCaseTest {
    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testRegisterProperty() {
        // Datos de prueba
        Property property = new Property();
        property.setName("Casa de prueba");
        property.setLocation("Bogota");
        property.setImage("Image");
        property.setAvailable(true);
        property.setPrice(new BigDecimal("1000.00"));
        property.setDate(new Date());

        // Configuración del mock
        when(propertyRepository.save(any(Property.class))).thenAnswer(invocation -> {

            // Ejecución del método
            Property result = propertyService.registerProperty(property);

            // Verificación
            Assertions.assertNotNull(result);
            Assertions.assertEquals("Casa de prueba", result.getName());
            Assertions.assertEquals("Ubicación de prueba", result.getLocation());
            Assertions.assertEquals("imagen.jpg", result.getImage());
            Assertions.assertTrue(result.isAvailable());
            Assertions.assertEquals(new BigDecimal("1000.00"), result.getPrice());

            return property;
        });
    }
}
