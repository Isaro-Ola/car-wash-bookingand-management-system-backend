package rw.auca.carwash.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.WashAttendant;
import rw.auca.carwash.repository.WashAttendantRepository;

@Service
public class WashAttendantService {

    @Autowired
    private WashAttendantRepository washAttendantRepository;

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
}