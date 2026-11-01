package rw.auca.carwash.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}