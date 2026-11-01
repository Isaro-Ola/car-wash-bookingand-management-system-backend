package rw.auca.carwash.controller;

import java.util.List;
import java.time.LocalDate;
import rw.auca.carwash.model.WashStatus;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.carwash.model.WashBooking;
import rw.auca.carwash.service.WashBookingService;

@RestController
@RequestMapping("/api/wash-bookings")
public class WashBookingController {

    @Autowired
    private WashBookingService washBookingService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody WashBooking washBooking) {
        String message = washBookingService.save(washBooking);
        if (message.equals("Wash booking saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("Wash booking date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<WashBooking>> getAll() {
        return new ResponseEntity<>(washBookingService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<WashBooking> washBooking = washBookingService.getById(id);
        if (washBooking.isPresent()) {
            return new ResponseEntity<>(washBooking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The wash booking with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody WashBooking washBooking) {
        String message = washBookingService.update(id, washBooking);
        if (message.equals("Wash booking updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = washBookingService.delete(id);
        if (message.equals("Wash booking deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The wash booking with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<WashBooking>> getByStatus(@RequestParam WashStatus status) {
        return new ResponseEntity<>(washBookingService.getByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<WashBooking>> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(washBookingService.getBetween(startDate, endDate), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam WashStatus status) {
        String message = washBookingService.updateStatus(id, status);
        if (message.equals("Wash booking status updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equals("The wash booking with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
}