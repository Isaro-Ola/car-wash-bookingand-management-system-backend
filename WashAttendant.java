package rw.auca.carwash.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "wash_attendant")
public class WashAttendant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "wash_bay_id")
    private WashBay washBay;

    @ManyToMany
    @JoinTable(
        name = "wash_attendant_service_type",
        joinColumns = @JoinColumn(name = "wash_attendant_id"),
        inverseJoinColumns = @JoinColumn(name = "service_type_id")
    )
    private List<ServiceType> serviceTypes = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public WashBay getWashBay() { return washBay; }
    public void setWashBay(WashBay washBay) { this.washBay = washBay; }
    public List<ServiceType> getServiceTypes() { return serviceTypes; }
    public void setServiceTypes(List<ServiceType> serviceTypes) { this.serviceTypes = serviceTypes; }
}