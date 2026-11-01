package rw.auca.carwash.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.Payment;
import rw.auca.carwash.model.WashBooking;
import rw.auca.carwash.model.WashStatus;
import rw.auca.carwash.repository.PaymentRepository;
import rw.auca.carwash.repository.WashBookingRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WashBookingRepository washBookingRepository;

    public String save(UUID washBookingId, Payment payment) {
        Optional<WashBooking> washBooking = washBookingRepository.findById(washBookingId);
        if (washBooking.isEmpty()) {
            return "The wash booking with that id does not exist";
        }
        if (washBooking.get().getStatus() != WashStatus.WASHED) {
            return "Payments are only for WASHED wash bookings";
        }
        payment.setPaidDate(LocalDate.now());
        payment.setWashBooking(washBooking.get());
        paymentRepository.save(payment);
        return "Payment saved successfully";
    }

    public List<Payment> getByCustomerPlateNumber(String plateNumber) {
        return paymentRepository.findPaymentsOfCustomer(plateNumber);
    }

    public String delete(UUID id) {
        if (!paymentRepository.existsById(id)) {
            return "The payment with that id does not exist";
        }
        paymentRepository.deleteById(id);
        return "Payment deleted successfully";
    }
}