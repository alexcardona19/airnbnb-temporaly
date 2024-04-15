package co.io.quind.airbnb.infraestructure;

import co.io.quind.airbnb.application.usecases.PropertyUseCase;
import co.io.quind.airbnb.domain.ports.in.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
 class PropertyControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PropertyService propertyService;

    private PropertyUseCase propertyUseCase;

    @BeforeEach
    public void setUp() {
        propertyUseCase = new PropertyUseCase(propertyService);
    }

   @Test
   void listProperties() throws Exception {
      when(propertyService.listProperties(0, 10000)).thenReturn(Collections.emptyList());

      mockMvc.perform(MockMvcRequestBuilders.get("/api/property?minPrice=0&maxPrice=10000"))
              .andExpect(status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
   }
}
