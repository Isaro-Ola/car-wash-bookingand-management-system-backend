package rw.auca.carwash.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.WashAttendant;

@Repository
public interface WashAttendantRepository extends JpaRepository<WashAttendant, UUID> {

    @Query("SELECT DISTINCT w FROM WashAttendant w JOIN w.serviceTypes s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<WashAttendant> findWashAttendantsByServiceTypeName(@Param("name") String name);
}