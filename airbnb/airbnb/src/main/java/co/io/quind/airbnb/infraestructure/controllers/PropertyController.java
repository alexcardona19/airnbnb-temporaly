package co.io.quind.airbnb.infraestructure.controllers;

import co.io.quind.airbnb.application.dto.ApiResponseDTO;
import co.io.quind.airbnb.application.dto.PropertyDTO;
import co.io.quind.airbnb.domain.exception.BusinessException;
import co.io.quind.airbnb.application.usecases.PropertyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static javax.swing.UIManager.put;

@RestController
@RequestMapping("/api/property")
public class PropertyController {
  private final PropertyUseCase propertyUseCase;

  @Autowired
  public PropertyController(PropertyUseCase propertyUseCase) {
    this.propertyUseCase = propertyUseCase;
  }

  @PostMapping
  public ResponseEntity<Object> registerProperty (@RequestBody @Valid PropertyDTO propertyDTO) {
    try {
      propertyUseCase.registerProperty(propertyDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (BusinessException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<List<PropertyDTO>> listProperties(
          @RequestParam("minPrice") double minPrice,
          @RequestParam("maxPrice") double maxPrice) {

    List<PropertyDTO> propertyDTOs = propertyUseCase.listProperties(minPrice, maxPrice);
    return new ResponseEntity<>(propertyDTOs, HttpStatus.OK);
  }

  @PutMapping("/rent/{id}")
  public ResponseEntity<Object> rentProperty(@Valid @PathVariable("id") Long id) {
    PropertyDTO rentedPropertyDTO = propertyUseCase.rentProperty(id);
    return ResponseEntity.ok().body(new ApiResponseDTO<>("La propiedad fue rentada exitosamente!", HttpStatus.OK.value(), rentedPropertyDTO));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> deleteProperty(@PathVariable("id") Long id)
  {
    try {
      propertyUseCase.deleteProperty(id);
      return ResponseEntity.ok("La propiedad ha sido borrada satisfactoriamente");
    } catch (BusinessException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
