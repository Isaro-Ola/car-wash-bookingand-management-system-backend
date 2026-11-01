package rw.auca.carwash.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.auca.carwash.model.ServiceType;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, UUID> {

    @Query("SELECT s FROM ServiceType s WHERE s.washAttendants IS EMPTY")
    List<ServiceType> findUnusedServiceTypes();
}