package rw.auca.carwash.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import rw.auca.carwash.model.ServiceType;
import rw.auca.carwash.model.WashBay;
import rw.auca.carwash.repository.ServiceTypeRepository;
import rw.auca.carwash.repository.WashBayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.WashAttendant;
import rw.auca.carwash.repository.WashAttendantRepository;

@Service
public class WashAttendantService {

    @Autowired
    private WashAttendantRepository washAttendantRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private WashBayRepository washBayRepository;

    public String save(WashAttendant washAttendant) {
        washAttendantRepository.save(washAttendant);
        return "Wash attendant saved successfully";
    }

    public List<WashAttendant> getAll() {
        return washAttendantRepository.findAll();
    }

    public Optional<WashAttendant> getById(UUID id) {
        return washAttendantRepository.findById(id);
    }

    public String update(UUID id, WashAttendant washAttendant) {
        Optional<WashAttendant> existing = washAttendantRepository.findById(id);
        if (existing.isEmpty()) {
            return "The wash attendant with that id does not exist";
        }
        WashAttendant found = existing.get();
        found.setFirstName(washAttendant.getFirstName());
        found.setLastName(washAttendant.getLastName());
        found.setPhone(washAttendant.getPhone());
        found.setWashBay(washAttendant.getWashBay());
        found.setServiceTypes(washAttendant.getServiceTypes());
        washAttendantRepository.save(found);
        return "Wash attendant updated successfully";
    }

    public String delete(UUID id) {
        if (!washAttendantRepository.existsById(id)) {
            return "The wash attendant with that id does not exist";
        }
        washAttendantRepository.deleteById(id);
        return "Wash attendant deleted successfully";
    }

    public List<WashAttendant> getByServiceTypeName(String name) {
        return washAttendantRepository.findWashAttendantsByServiceTypeName(name);
    }

    public String addServiceType(UUID washAttendantId, UUID serviceTypeId) {
        Optional<WashAttendant> attendant = washAttendantRepository.findById(washAttendantId);
        if (attendant.isEmpty()) {
            return "The wash attendant with that id does not exist";
        }
        Optional<ServiceType> serviceType = serviceTypeRepository.findById(serviceTypeId);
        if (serviceType.isEmpty()) {
            return "That service type does not exist";
        }
        WashAttendant found = attendant.get();
        if (found.getServiceTypes().contains(serviceType.get())) {
            return "The wash attendant already has that service type";
        }
        found.getServiceTypes().add(serviceType.get());
        washAttendantRepository.save(found);
        return "Service type added to the wash attendant";
    }

    public String changeWashBay(UUID washAttendantId, int bayNumber) {
        Optional<WashAttendant> attendant = washAttendantRepository.findById(washAttendantId);
        if (attendant.isEmpty()) {
            return "The wash attendant with that id does not exist";
        }
        Optional<WashBay> bay = washBayRepository.findByBayNumber(bayNumber);
        if (bay.isEmpty()) {
            return "The wash bay with that bayNumber does not exist";
        }
        WashAttendant found = attendant.get();
        boolean alreadyHere = found.getWashBay() != null && found.getWashBay().getId().equals(bay.get().getId());
        if (!alreadyHere && washAttendantRepository.existsByWashBay(bay.get())) {
            return "That wash bay is taken by another wash attendant";
        }
        found.setWashBay(bay.get());
        washAttendantRepository.save(found);
        return "Wash bay changed successfully";
    }
}