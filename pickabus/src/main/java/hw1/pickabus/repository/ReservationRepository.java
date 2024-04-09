package hw1.pickabus.repository;

import hw1.pickabus.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationRepository {
    private final List<Reservation> reservations = new ArrayList<>();
    private Integer nextId = 1;

    public Reservation save(Reservation reservation) {
        reservation.setId(nextId++);
        reservations.add(reservation);
        return reservation;
    }

    public Optional<Reservation> findByToken(String token) {
        return reservations.stream().filter(r -> r.getToken().equals(token)).findFirst();
    }

    public List<Reservation> findByTripId(Integer tripId) {
        return reservations.stream()
                .filter(reservation -> tripId.equals(reservation.getTripId()))
                .collect(Collectors.toList());
    }
}
