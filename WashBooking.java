package rw.auca.carwash.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "wash_booking")
public class WashBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    private WashStatus status;

    private String timeSlot;
    private String vehicleModel;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "wash_attendant_id")
    private WashAttendant washAttendant;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    public WashStatus getStatus() { return status; }
    public void setStatus(WashStatus status) { this.status = status; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public WashAttendant getWashAttendant() { return washAttendant; }
    public void setWashAttendant(WashAttendant washAttendant) { this.washAttendant = washAttendant; }
}