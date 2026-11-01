package rw.auca.carwash.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "wash_bay")
public class WashBay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int bayNumber;

    private String name;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public int getBayNumber() { return bayNumber; }
    public void setBayNumber(int bayNumber) { this.bayNumber = bayNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}