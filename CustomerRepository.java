package rw.auca.carwash.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT c FROM WashBooking b JOIN b.customer c WHERE b.washAttendant.id = :washAttendantId")
    List<Customer> findCustomersOfWashAttendant(@Param("washAttendantId") UUID washAttendantId);
}