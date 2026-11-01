package rw.auca.carwash.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.carwash.model.WashAttendant;
import rw.auca.carwash.service.WashAttendantService;

@RestController
@RequestMapping("/api/wash-attendants")
public class WashAttendantController {

    @Autowired
    private WashAttendantService washAttendantService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody WashAttendant washAttendant) {
        String message = washAttendantService.save(washAttendant);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WashAttendant>> getAll() {
        return new ResponseEntity<>(washAttendantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<WashAttendant> washAttendant = washAttendantService.getById(id);
        if (washAttendant.isPresent()) {
            return new ResponseEntity<>(washAttendant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The wash attendant with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody WashAttendant washAttendant) {
        String message = washAttendantService.update(id, washAttendant);
        if (message.equals("Wash attendant updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = washAttendantService.delete(id);
        if (message.equals("Wash attendant deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-service-type")
    public ResponseEntity<List<WashAttendant>> getByServiceType(@RequestParam String name) {
        return new ResponseEntity<>(washAttendantService.getByServiceTypeName(name), HttpStatus.OK);
    }

    @PutMapping("/{washAttendantId}/service-types/{serviceTypeId}")
    public ResponseEntity<String> addServiceType(@PathVariable UUID washAttendantId, @PathVariable UUID serviceTypeId) {
        String message = washAttendantService.addServiceType(washAttendantId, serviceTypeId);
        if (message.equals("Service type added to the wash attendant")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The wash attendant already has that service type")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{washAttendantId}/wash-bay")
    public ResponseEntity<String> changeWashBay(@PathVariable UUID washAttendantId, @RequestParam int bayNumber) {
        String message = washAttendantService.changeWashBay(washAttendantId, bayNumber);
        if (message.equals("Wash bay changed successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("That wash bay is taken by another wash attendant")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}