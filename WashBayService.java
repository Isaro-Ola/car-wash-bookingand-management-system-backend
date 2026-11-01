package rw.auca.carwash.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.WashBay;
import rw.auca.carwash.repository.WashBayRepository;

@Service
public class WashBayService {

    @Autowired
    private WashBayRepository washBayRepository;

    public String save(WashBay washBay) {
        washBayRepository.save(washBay);
        return "Wash bay saved successfully";
    }

    public List<WashBay> getAll() {
        return washBayRepository.findAll();
    }

    public Optional<WashBay> getById(UUID id) {
        return washBayRepository.findById(id);
    }

    public String update(UUID id, WashBay washBay) {
        Optional<WashBay> existing = washBayRepository.findById(id);
        if (existing.isEmpty()) {
            return "The wash bay with that id does not exist";
        }
        WashBay found = existing.get();
        found.setBayNumber(washBay.getBayNumber());
        found.setName(washBay.getName());
        washBayRepository.save(found);
        return "Wash bay updated successfully";
    }

    public String delete(UUID id) {
        if (!washBayRepository.existsById(id)) {
            return "The wash bay with that id does not exist";
        }
        washBayRepository.deleteById(id);
        return "Wash bay deleted successfully";
    }
}