package rw.auca.carwash.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.carwash.model.WashBay;
import rw.auca.carwash.service.WashBayService;

@RestController
@RequestMapping("/api/wash-bays")
public class WashBayController {

    @Autowired
    private WashBayService washBayService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody WashBay washBay) {
        String message = washBayService.save(washBay);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WashBay>> getAll() {
        return new ResponseEntity<>(washBayService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<WashBay> washBay = washBayService.getById(id);
        if (washBay.isPresent()) {
            return new ResponseEntity<>(washBay.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The wash bay with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody WashBay washBay) {
        String message = washBayService.update(id, washBay);
        if (message.equals("Wash bay updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = washBayService.delete(id);
        if (message.equals("Wash bay deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}