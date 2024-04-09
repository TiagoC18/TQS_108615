package hw1.pickabus;

import hw1.pickabus.model.Reservation;
import hw1.pickabus.model.Trip;
import hw1.pickabus.repository.ReservationRepository;
import hw1.pickabus.repository.TripRepository;
import hw1.pickabus.service.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setUp() {
        // Initialize mocks and inject them into the tested service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulReservation() {
        Trip trip = new Trip(1, "Origin", "Destination", "9:00 AM", 50, 10);
        when(tripRepository.findById(1)).thenReturn(java.util.Optional.of(trip));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(i -> i.getArguments()[0]); // Mock saving the reservation by returning it directly

        Reservation reservation = new Reservation(null, "John Doe", "john@example.com", 1, 2, null);
        Reservation savedReservation = reservationService.createReservation(reservation);

        Assertions.assertNotNull(savedReservation);
        Assertions.assertTrue(savedReservation.getNumberOfTickets() <= trip.getSeats());
    }

}
