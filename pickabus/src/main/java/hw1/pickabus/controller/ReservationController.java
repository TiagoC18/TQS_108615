package hw1.pickabus.controller;

import hw1.pickabus.model.Reservation;
import hw1.pickabus.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<Reservation> getReservationByToken(@PathVariable String token) {
        Optional<Reservation> reservation = reservationService.findReservationByToken(token);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

     @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Reservation>> getReservationsByTripId(@PathVariable Integer tripId) {
        List<Reservation> reservations = reservationService.findReservationsByTripId(tripId);
        if (reservations.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(reservations);
        }
    }

}
