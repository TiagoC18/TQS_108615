package hw1.pickabus.service;

import hw1.pickabus.model.Trip;
import hw1.pickabus.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // Find trips based on origin and destination
    public List<Trip> findTrips(String origin, String destination) {
        return tripRepository.findTripsByOriginAndDestination(origin, destination);
    }

    // Check if there are enough available seats for a given trip
    public boolean areSeatsAvailable(Integer tripId, int seatsRequested) {
        return tripRepository.findById(tripId)
                .map(trip -> trip.getSeats() >= seatsRequested)
                .orElse(false);
    }

    // Book a number of seats for a given trip
    public boolean bookSeats(Integer tripId, int seatsRequested) {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        if (optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();
            if (trip.getSeats() >= seatsRequested) {
                trip.setSeats(trip.getSeats() - seatsRequested);
                tripRepository.update(trip); // Update the trip in the repository
                return true;
            }
        }
        return false;
    }

    public Set<String> findAvailableCities() {
        List<Trip> allTrips = tripRepository.findAllTrips();
        Set<String> cities = new HashSet<>();
        for (Trip trip : allTrips) {
            cities.add(trip.getOrigin());
            cities.add(trip.getDestination());
        }
        return cities;
    }
}
