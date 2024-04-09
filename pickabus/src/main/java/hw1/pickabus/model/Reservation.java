package hw1.pickabus.model;

import java.util.UUID;

public class Reservation {
    private Integer id;
    private String passengerName;
    private String passengerEmail;
    private Integer tripId;
    private int numberOfTickets;
    private String token;

    public Reservation(Integer id, String passengerName, String passengerEmail, Integer tripId, int numberOfTickets,
            String token) {
        this.id = id;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.tripId = tripId;
        this.numberOfTickets = numberOfTickets;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void generateToken() {
        this.token = UUID.randomUUID().toString();
    }

}
