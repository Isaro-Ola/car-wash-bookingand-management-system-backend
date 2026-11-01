package rw.auca.carwash.service;

import java.util.List;
import java.time.LocalDate;
import rw.auca.carwash.model.WashStatus;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.WashBooking;
import rw.auca.carwash.repository.WashBookingRepository;

@Service
public class WashBookingService {

    @Autowired
    private WashBookingRepository washBookingRepository;

    public String save(WashBooking washBooking) {
        washBookingRepository.save(washBooking);
        return "Wash booking saved successfully";
    }

    public List<WashBooking> getAll() {
        return washBookingRepository.findAll();
    }

    public Optional<WashBooking> getById(UUID id) {
        return washBookingRepository.findById(id);
    }

    public String update(UUID id, WashBooking washBooking) {
        Optional<WashBooking> existing = washBookingRepository.findById(id);
        if (existing.isEmpty()) {
            return "The wash booking with that id does not exist";
        }
        WashBooking found = existing.get();
        found.setBookingDate(washBooking.getBookingDate());
        found.setTimeSlot(washBooking.getTimeSlot());
        found.setVehicleModel(washBooking.getVehicleModel());
        found.setCustomer(washBooking.getCustomer());
        found.setWashAttendant(washBooking.getWashAttendant());
        washBookingRepository.save(found);
        return "Wash booking updated successfully";
    }

    public String delete(UUID id) {
        if (!washBookingRepository.existsById(id)) {
            return "The wash booking with that id does not exist";
        }
        washBookingRepository.deleteById(id);
        return "Wash booking deleted successfully";
    }

    public List<WashBooking> getByStatus(WashStatus status) {
        return washBookingRepository.findByStatusOrderByBookingDateAsc(status);
    }

    public List<WashBooking> getBetween(LocalDate start, LocalDate end) {
        return washBookingRepository.findByBookingDateBetweenOrderByBookingDateAsc(start, end);
    }
}