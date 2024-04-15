package co.io.quind.airbnb.application;

import co.io.quind.airbnb.application.dto.PropertyDTO;
import co.io.quind.airbnb.application.usecases.PropertyUseCase;
import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.in.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

 class PropertyUseCaseTest {
    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyUseCase propertyUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testRegisterProperty() {
        // Given
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(1L);
        propertyDTO.setName("Casa de prueba");
        propertyDTO.setPrice(100.0);

        Property property = propertyDTO.toDomain();

        when(propertyService.registerProperty(property)).thenReturn(property);

        // When
        PropertyDTO result = propertyUseCase.registerProperty(propertyDTO);

        // Then
        assertEquals(propertyDTO.getId(), result.getId());
        assertEquals(propertyDTO.getName(), result.getName());
        assertEquals(propertyDTO.getPrice(), result.getPrice());
    }

    @Test
     void testListProperties() {
        // Given
        double minPrice = 50.0;
        double maxPrice = 150.0;

        List<Property> properties = new ArrayList<>();
        properties.add(new Property(1L, "Casa 1","Medellin","URL",true, 100.0,new Date(),false));
        properties.add(new Property(1L, "Casa 2","Bogota","URL",true, 2500000.0,new Date(),false));

        when(propertyService.listProperties(minPrice, maxPrice)).thenReturn(properties);

        // When
        List<PropertyDTO> result = propertyUseCase.listProperties(minPrice, maxPrice);

        // Then
        assertEquals(2, result.size());
        assertEquals("Casa 1", result.get(0).getName());
        assertEquals("Casa 2", result.get(1).getName());
    }

    @Test
     void testDeleteProperty() {
        // Given
        long id = 1L;

        // When
        propertyUseCase.deleteProperty(id);

        // Then
        Mockito.verify(propertyService, times(1)).deleteProperty(id);
    }

    @Test
     void testRentProperty() {
        // Given
        long id = 1L;
        Property property = new Property(id, "Casa", "0","0",true,150000.0,new Date(),false);

        when(propertyService.rentProperty(id)).thenReturn(property);

        // When
        PropertyDTO result = propertyUseCase.rentProperty(id);

        // Then
        assertEquals(id, result.getId());
        assertEquals("Casa", result.getName());
        assertEquals(150000.0, result.getPrice());
    }

    @Test
     void testEditProperty() {
        // Given
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(1L);
        propertyDTO.setName("Casa 1");
        propertyDTO.setPrice(100.0);

        Property propertyToEdit = propertyDTO.toDomain();

        when(propertyService.editProperty(propertyToEdit)).thenReturn(propertyToEdit);

        // When
        PropertyDTO result = propertyUseCase.editProperty(propertyDTO);

        // Then
        assertEquals(propertyDTO.getId(), result.getId());
        assertEquals(propertyDTO.getName(), result.getName());
        assertEquals(propertyDTO.getPrice(), result.getPrice());
    }
}
