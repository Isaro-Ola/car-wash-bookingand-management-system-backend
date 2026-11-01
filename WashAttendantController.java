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
}