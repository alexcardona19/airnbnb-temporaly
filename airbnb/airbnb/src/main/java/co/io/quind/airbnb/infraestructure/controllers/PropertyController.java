package co.io.quind.airbnb.infraestructure.controllers;

import co.io.quind.airbnb.application.dto.PropertyDTO;
import co.io.quind.airbnb.domain.exception.BusinessException;
import co.io.quind.airbnb.application.usecases.PropertyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/property")
public class PropertyController {
  private final PropertyUseCase propertyUseCase;

  @Autowired
  public PropertyController(PropertyUseCase propertyUseCase) {
    this.propertyUseCase = propertyUseCase;
  }

  @PostMapping
  public ResponseEntity<Object> registerProperty (@RequestBody @Valid  PropertyDTO propertyDTO) {
    try {
      propertyUseCase.registerProperty(propertyDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (BusinessException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
