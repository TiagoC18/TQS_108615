package hw1.pickabus.controller;

import hw1.pickabus.model.Trip;
import hw1.pickabus.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // Endpoint to search for trips based on origin and destination
    @GetMapping("/search")
    public ResponseEntity<List<Trip>> searchTrips(
            @RequestParam String origin,
            @RequestParam String destination) {
        List<Trip> trips = tripService.findTrips(origin, destination);
        if (trips.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(trips);
        }
    }

    // Endpoint to book a trip
    @PostMapping("/booking")
    public ResponseEntity<?> bookTrip(
            @RequestParam Integer tripId,
            @RequestParam int seatsRequested) {
        boolean seatsAvailable = tripService.areSeatsAvailable(tripId, seatsRequested);
        if (!seatsAvailable) {
            return ResponseEntity.badRequest().body("Not enough seats available.");
        }

        boolean bookingSuccessful = tripService.bookSeats(tripId, seatsRequested);
        if (bookingSuccessful) {
            return ResponseEntity.ok().body("Booking successful.");
        } else {
            return ResponseEntity.internalServerError().body("Booking failed.");
        }
    }

    @GetMapping("/cities")
    public Set<String> getAvailableCities() {
        return tripService.findAvailableCities();
    }
}
