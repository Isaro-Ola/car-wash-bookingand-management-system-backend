package rw.auca.carwash.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String method;
    private String reference;
    private int amount;
    private LocalDate paidDate;

    @ManyToOne
    @JoinColumn(name = "wash_booking_id")
    private WashBooking washBooking;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public LocalDate getPaidDate() { return paidDate; }
    public void setPaidDate(LocalDate paidDate) { this.paidDate = paidDate; }
    public WashBooking getWashBooking() { return washBooking; }
    public void setWashBooking(WashBooking washBooking) { this.washBooking = washBooking; }
}