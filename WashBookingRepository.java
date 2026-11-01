package rw.auca.carwash.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.WashBooking;
import rw.auca.carwash.model.WashStatus;

@Repository
public interface WashBookingRepository extends JpaRepository<WashBooking, UUID> {

    long countByBookingDate(LocalDate bookingDate);

    long countByStatus(WashStatus status);

    List<WashBooking> findByStatusOrderByBookingDateAsc(WashStatus status);

    List<WashBooking> findByBookingDateBetweenOrderByBookingDateAsc(LocalDate start, LocalDate end);

    boolean existsByWashAttendantIdAndBookingDateAndStatusNot(UUID washAttendantId, LocalDate bookingDate, WashStatus status);

    @Query("SELECT b.status, COUNT(b) FROM WashBooking b GROUP BY b.status")
    List<Object[]> findStatusCounts();

    @Modifying
    @Query("UPDATE WashBooking b SET b.status = :cancelled WHERE b.washAttendant.id = :washAttendantId AND b.bookingDate = :date AND b.status <> :washed")
    int cancelDay(@Param("washAttendantId") UUID washAttendantId, @Param("date") LocalDate date, @Param("cancelled") WashStatus cancelled, @Param("washed") WashStatus washed);
}