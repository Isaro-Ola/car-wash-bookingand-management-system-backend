package rw.auca.carwash.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.WashBooking;
import rw.auca.carwash.model.WashStatus;

@Repository
public interface WashBookingRepository extends JpaRepository<WashBooking, UUID> {

    List<WashBooking> findByStatusOrderByBookingDateAsc(WashStatus status);

    List<WashBooking> findByBookingDateBetweenOrderByBookingDateAsc(LocalDate start, LocalDate end);
}