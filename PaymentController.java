package rw.auca.carwash.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.carwash.model.Payment;
import rw.auca.carwash.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID washBookingId, @RequestBody Payment payment) {
        String message = paymentService.save(washBookingId, payment);
        if (message.equals("Payment saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("The wash booking with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer/{plateNumber}")
    public ResponseEntity<List<Payment>> getByCustomer(@PathVariable String plateNumber) {
        return new ResponseEntity<>(paymentService.getByCustomerPlateNumber(plateNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = paymentService.delete(id);
        if (message.equals("Payment deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}