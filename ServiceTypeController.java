package rw.auca.carwash.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.carwash.model.ServiceType;
import rw.auca.carwash.service.ServiceTypeService;

@RestController
@RequestMapping("/api/service-types")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ServiceType serviceType) {
        String message = serviceTypeService.save(serviceType);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceType>> getAll() {
        return new ResponseEntity<>(serviceTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<ServiceType> serviceType = serviceTypeService.getById(id);
        if (serviceType.isPresent()) {
            return new ResponseEntity<>(serviceType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The service type with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ServiceType serviceType) {
        String message = serviceTypeService.update(id, serviceType);
        if (message.equals("Service type updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = serviceTypeService.delete(id);
        if (message.equals("Service type deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}