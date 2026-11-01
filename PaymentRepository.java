package rw.auca.carwash.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.Payment;
import rw.auca.carwash.model.WashBooking;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query("SELECT p FROM Payment p WHERE p.washBooking.customer.plateNumber = :plateNumber ORDER BY p.paidDate DESC")
    List<Payment> findPaymentsOfCustomer(@Param("plateNumber") String plateNumber);

    boolean existsByWashBooking(WashBooking washBooking);
}