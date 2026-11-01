package rw.auca.carwash.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.WashStatus;
import rw.auca.carwash.repository.CustomerRepository;
import rw.auca.carwash.repository.PaymentRepository;
import rw.auca.carwash.repository.WashAttendantRepository;
import rw.auca.carwash.repository.WashBayRepository;
import rw.auca.carwash.repository.WashBookingRepository;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WashAttendantRepository washAttendantRepository;

    @Autowired
    private WashBayRepository washBayRepository;

    @Autowired
    private WashBookingRepository washBookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("customers", customerRepository.count());
        summary.put("washAttendants", washAttendantRepository.count());
        summary.put("freeWashBays", (long) washBayRepository.findFreeWashBays().size());
        summary.put("bookingsToday", washBookingRepository.countByBookingDate(LocalDate.now()));
        summary.put("booked", washBookingRepository.countByStatus(WashStatus.BOOKED));
        summary.put("payments", paymentRepository.count());
        return summary;
    }
}