package hw1.pickabus.repository;

import hw1.pickabus.model.Trip;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TripRepository {
    private List<Trip> trips = new ArrayList<>();


    public TripRepository() {
        // Existing trips
        trips.add(new Trip(1, "Berlin", "Paris", "9:00 AM", 120, 40));
        trips.add(new Trip(2, "Madrid", "Lisbon", "10:00 AM", 90, 30));
        trips.add(new Trip(3, "Rome", "Vienna", "11:00 AM", 130, 25));
        trips.add(new Trip(4, "Paris", "Berlin", "7:00 AM", 120, 40));
        trips.add(new Trip(5, "Lisbon", "Madrid", "8:00 AM", 90, 30));
        trips.add(new Trip(6, "Vienna", "Rome", "9:00 AM", 130, 25));
        trips.add(new Trip(7, "Berlin", "Madrid", "8:00 PM", 150, 45));
        trips.add(new Trip(8, "Madrid", "Berlin", "9:00 PM", 150, 45));
        trips.add(new Trip(9, "Berlin", "Lisbon", "10:00 PM", 140, 40));
        trips.add(new Trip(10, "Lisbon", "Berlin", "11:00 PM", 140, 40));
        trips.add(new Trip(11, "Berlin", "Rome", "5:00 AM", 90, 60));
        trips.add(new Trip(12, "Rome", "Berlin", "6:00 AM", 90, 60));
        trips.add(new Trip(13, "Berlin", "Vienna", "7:00 AM", 80, 50));
        trips.add(new Trip(14, "Vienna", "Berlin", "8:00 AM", 80, 50));
        trips.add(new Trip(15, "Madrid", "Rome", "10:00 AM", 70, 35));
        trips.add(new Trip(16, "Rome", "Madrid", "11:00 AM", 70, 35));
        trips.add(new Trip(17, "Madrid", "Vienna", "7:00 AM", 90, 30));
        trips.add(new Trip(18, "Vienna", "Madrid", "8:00 AM", 90, 30));
        trips.add(new Trip(19, "Paris", "Madrid", "9:00 AM", 100, 25));
        trips.add(new Trip(20, "Paris", "Lisbon", "12:00 PM", 110, 50));
        trips.add(new Trip(21, "Lisbon", "Paris", "1:00 PM", 110, 50));
        trips.add(new Trip(22, "Paris", "Rome", "2:00 PM", 115, 55));
        trips.add(new Trip(23, "Rome", "Paris", "3:00 PM", 115, 55));
        trips.add(new Trip(24, "Paris", "Vienna", "4:00 PM", 130, 45));
        trips.add(new Trip(25, "Vienna", "Paris", "5:00 PM", 130, 45));
        trips.add(new Trip(26, "Lisbon", "Rome", "6:00 PM", 140, 40));
        trips.add(new Trip(27, "Rome", "Lisbon", "7:00 PM", 140, 40));
        trips.add(new Trip(28, "Lisbon", "Vienna", "8:00 PM", 150, 35));
        trips.add(new Trip(29, "Vienna", "Lisbon", "9:00 PM", 150, 35));
    }
    

    public List<Trip> findTripsByOriginAndDestination(String origin, String destination) {
        return trips.stream()
                .filter(trip -> trip.getOrigin().equalsIgnoreCase(origin)
                        && trip.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }

    public Optional<Trip> findById(Integer id) {
        return trips.stream()
                .filter(trip -> trip.getId().equals(id))
                .findFirst();
    }

    public void update(Trip updatedTrip) {
        // Attempt to find the trip in the list
        for (int i = 0; i < trips.size(); i++) {
            Trip trip = trips.get(i);
            if (trip.getId().equals(updatedTrip.getId())) {
                // Replace the old trip with the updated trip
                trips.set(i, updatedTrip);
                return;
            }
        }
    }

    public List<Trip> findAllTrips() {
        return new ArrayList<>(trips); // Returns a new list containing all trips
    }
    

    

}
