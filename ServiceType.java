package rw.auca.carwash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "service_type")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "serviceTypes")
    @JsonIgnore
    private List<WashAttendant> washAttendants;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<WashAttendant> getWashAttendants() { return washAttendants; }
    public void setWashAttendants(List<WashAttendant> washAttendants) { this.washAttendants = washAttendants; }
}