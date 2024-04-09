package hw1.pickabus;

import hw1.pickabus.model.Trip;
import hw1.pickabus.repository.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hw1.pickabus.service.TripService;;;

 class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testFindTrips() {
        // Setup
        Trip trip1 = new Trip(1, "City A", "City B", "10:00", 100, 50);
        when(tripRepository.findTripsByOriginAndDestination("City A", "City B")).thenReturn(Arrays.asList(trip1));

        // Action
        List<Trip> trips = tripService.findTrips("City A", "City B");

        // Assertion
        assertEquals(1, trips.size());
        assertEquals(trip1, trips.get(0));
    }

    @Test
     void testAreSeatsAvailable() {
        // Setup
        when(tripRepository.findById(1)).thenReturn(Optional.of(new Trip(1, "City A", "City B", "10:00", 100, 50)));

        // Action & Assertion
        assertTrue(tripService.areSeatsAvailable(1, 5));
        assertFalse(tripService.areSeatsAvailable(1, 60)); // More seats requested than available
    }

    @Test
     void testBookSeatsSuccess() {
        // Setup
        Trip trip = new Trip(1, "City A", "City B", "10:00", 100, 50);
        when(tripRepository.findById(1)).thenReturn(Optional.of(trip));

        // Action
        boolean bookingResult = tripService.bookSeats(1, 5);

        // Assertion
        assertTrue(bookingResult);
        assertEquals(45, trip.getSeats()); // 50 - 5 seats booked
    }

    @Test
     void testBookSeatsFailure() {
        // Setup
        when(tripRepository.findById(1)).thenReturn(Optional.of(new Trip(1, "City A", "City B", "10:00", 100, 2)));

        // Action
        boolean bookingResult = tripService.bookSeats(1, 5);

        // Assertion
        assertFalse(bookingResult); // Booking should fail due to insufficient seats
    }

    @Test
     void testFindAvailableCities() {
        // Setup
        when(tripRepository.findAllTrips()).thenReturn(Arrays.asList(
                new Trip(1, "City A", "City B", "10:00", 100, 50),
                new Trip(2, "City C", "City D", "11:00", 100, 30)
        ));

        // Action
        HashSet<String> expectedCities = new HashSet<>(Arrays.asList("City A", "City B", "City C", "City D"));
        Set<String> actualCities = tripService.findAvailableCities();

        // Assertion
        assertEquals(expectedCities, actualCities);
    }
}
