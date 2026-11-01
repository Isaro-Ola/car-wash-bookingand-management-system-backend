package rw.auca.carwash.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.WashBay;

@Repository
public interface WashBayRepository extends JpaRepository<WashBay, UUID> {

    @Query("SELECT b FROM WashBay b WHERE NOT EXISTS (SELECT a FROM WashAttendant a WHERE a.washBay = b) ORDER BY b.bayNumber ASC")
    List<WashBay> findFreeWashBays();

    Optional<WashBay> findByBayNumber(int bayNumber);
}