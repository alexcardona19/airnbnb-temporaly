package co.io.quind.airbnb.service;

import co.io.quind.airbnb.application.dto.PropertyDTO;
import co.io.quind.airbnb.domain.models.Property;
import co.io.quind.airbnb.domain.ports.in.service.PropertyService;
import co.io.quind.airbnb.infraestructure.adapters.repositories.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

 class PropertyServiceTest {
     @Mock
     private PropertyRepository propertyRepository;

     @BeforeEach
     public void setUp() {
         MockitoAnnotations.openMocks(this);
     }

     @Test
      void testRegisterPropertyWithoutName() {
         // Datos de prueba sin nombre
         PropertyDTO propertyDTO = PropertyDTO.builder()
                 .id(1L)
                 .location("TestLocation")
                 .image("TestImage")
                 .isAvailable(true)
                 .price(100.0)
                 .isDeleted(false)
                 .build();

         Property property = propertyDTO.toDomain();

         // Llamada al método a probar y verificación de excepción
         PropertyService propertyService = new PropertyService(propertyRepository);
         assertThrows(RuntimeException.class, () -> propertyService.registerProperty(property));

         // Verificar llamadas a los métodos del repositorio
         verifyNoInteractions(propertyRepository);
     }

     @Test
      void testRegisterPropertyWithDuplicateName() {
         // Datos de prueba
         PropertyDTO propertyDTO = PropertyDTO.builder()
                 .id(1L)
                 .name("TestProperty")
                 .location("TestLocation")
                 .image("TestImage")
                 .isAvailable(true)
                 .price(100.0)
                 .isDeleted(false)
                 .build();

         Property property = propertyDTO.toDomain();

         // Configuración del comportamiento del repositorio para simular un nombre duplicado
         when(propertyRepository.existsByName(anyString())).thenReturn(true);

         // Llamada al método a probar y verificación de excepción
         PropertyService propertyService = new PropertyService(propertyRepository);
         assertThrows(RuntimeException.class, () -> propertyService.registerProperty(property));

         // Verificar llamada al método del repositorio para comprobar el nombre duplicado
         verify(propertyRepository, times(1)).existsByName(anyString());
         verifyNoMoreInteractions(propertyRepository);
     }

     @Test
      void testRegisterPropertySuccess() {
         // Datos de prueba
         PropertyDTO propertyDTO = PropertyDTO.builder()
                 .id(1L)
                 .name("TestProperty")
                 .location("Cali")
                 .image("TestImage")
                 .isAvailable(true)
                 .price(3000000)
                 .date(new Date())
                 .isDeleted(false)
                 .build();

         Property property = propertyDTO.toDomain();

         // Configuración del comportamiento del repositorio
         when(propertyRepository.existsByName(anyString())).thenReturn(false);
         when(propertyRepository.save(property)).thenReturn(property);

         // Llamada al método a probar
         PropertyService propertyService = new PropertyService(propertyRepository);
         Property result = propertyService.registerProperty(property);

         // Verificaciones
         assertNotNull(result);
         assertEquals(propertyDTO.getId(), result.getId());
         assertEquals(propertyDTO.getName(), result.getName());
         assertEquals(propertyDTO.getLocation(), result.getLocation());
         assertEquals(propertyDTO.getImage(), result.getImage());
         assertEquals(propertyDTO.isAvailable(), result.isAvailable());
         assertEquals(propertyDTO.getPrice(), result.getPrice());
         assertEquals(propertyDTO.isDeleted(), result.isDeleted());

         // Verificar llamadas a los métodos del repositorio
         verify(propertyRepository, times(1)).existsByName(anyString());
         verify(propertyRepository, times(1)).save(property);
         verifyNoMoreInteractions(propertyRepository);
     }

 }
