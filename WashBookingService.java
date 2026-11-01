package rw.auca.carwash.service;

import java.util.List;
import java.time.LocalDate;
import rw.auca.carwash.model.WashStatus;
import java.util.Optional;
import java.util.UUID;
import rw.auca.carwash.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.WashBooking;
import rw.auca.carwash.repository.WashBookingRepository;

@Service
public class WashBookingService {

    @Autowired
    private WashBookingRepository washBookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public String save(WashBooking washBooking) {
        if (washBooking.getBookingDate().isBefore(LocalDate.now())) {
            return "Wash booking date cannot be in the past";
        }
        boolean taken = washBookingRepository.existsByWashAttendantIdAndBookingDateAndStatusNot(
                washBooking.getWashAttendant().getId(), washBooking.getBookingDate(), WashStatus.CANCELLED);
        if (taken) {
            return "Wash attendant is already booked on that date";
        }
        washBooking.setStatus(WashStatus.BOOKED);
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
        Optional<WashBooking> existing = washBookingRepository.findById(id);
        if (existing.isEmpty()) {
            return "The wash booking with that id does not exist";
        }
        if (paymentRepository.existsByWashBooking(existing.get())) {
            return "That wash booking has payments, delete them first";
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

    public String updateStatus(UUID id, WashStatus newStatus) {
        Optional<WashBooking> existing = washBookingRepository.findById(id);
        if (existing.isEmpty()) {
            return "The wash booking with that id does not exist";
        }
        WashBooking found = existing.get();
        WashStatus current = found.getStatus();
        boolean allowed = false;
        if (current == WashStatus.BOOKED && (newStatus == WashStatus.IN_PROGRESS || newStatus == WashStatus.CANCELLED)) {
            allowed = true;
        } else if (current == WashStatus.IN_PROGRESS && (newStatus == WashStatus.WASHED || newStatus == WashStatus.CANCELLED)) {
            allowed = true;
        }
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        found.setStatus(newStatus);
        washBookingRepository.save(found);
        return "Wash booking status updated successfully";
    }
}