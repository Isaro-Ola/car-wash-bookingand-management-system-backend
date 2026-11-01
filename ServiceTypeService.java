package rw.auca.carwash.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.ServiceType;
import rw.auca.carwash.repository.ServiceTypeRepository;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public String save(ServiceType serviceType) {
        serviceTypeRepository.save(serviceType);
        return "Service type saved successfully";
    }

    public List<ServiceType> getAll() {
        return serviceTypeRepository.findAll();
    }

    public Optional<ServiceType> getById(UUID id) {
        return serviceTypeRepository.findById(id);
    }

    public String update(UUID id, ServiceType serviceType) {
        Optional<ServiceType> existing = serviceTypeRepository.findById(id);
        if (existing.isEmpty()) {
            return "The service type with that id does not exist";
        }
        ServiceType found = existing.get();
        found.setName(serviceType.getName());
        serviceTypeRepository.save(found);
        return "Service type updated successfully";
    }

    public String delete(UUID id) {
        if (!serviceTypeRepository.existsById(id)) {
            return "The service type with that id does not exist";
        }
        serviceTypeRepository.deleteById(id);
        return "Service type deleted successfully";
    }
}