package rw.auca.carwash.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.auca.carwash.model.Customer;
import rw.auca.carwash.repository.CustomerRepository;
import rw.auca.carwash.repository.WashAttendantRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WashAttendantRepository washAttendantRepository;

    public String save(Customer customer) {
        customerRepository.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getById(UUID id) {
        return customerRepository.findById(id);
    }

    public String update(UUID id, Customer customer) {
        Optional<Customer> existing = customerRepository.findById(id);
        if (existing.isEmpty()) {
            return "The customer with that id does not exist";
        }
        Customer found = existing.get();
        found.setFirstName(customer.getFirstName());
        found.setLastName(customer.getLastName());
        found.setPlateNumber(customer.getPlateNumber());
        found.setPhone(customer.getPhone());
        customerRepository.save(found);
        return "Customer updated successfully";
    }

    public String delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            return "The customer with that id does not exist";
        }
        customerRepository.deleteById(id);
        return "Customer deleted successfully";
    }


    public List<Customer> getByLastName(String lastName) {
        return customerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public boolean washAttendantExists(UUID washAttendantId) {
        return washAttendantRepository.existsById(washAttendantId);
    }

    public List<Customer> getByWashAttendant(UUID washAttendantId) {
        return customerRepository.findCustomersOfWashAttendant(washAttendantId);
    }
}