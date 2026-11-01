package rw.auca.carwash.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.WashAttendant;

@Repository
public interface WashAttendantRepository extends JpaRepository<WashAttendant, UUID> {
}