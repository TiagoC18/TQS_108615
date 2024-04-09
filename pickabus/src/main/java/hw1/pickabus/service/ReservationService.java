package hw1.pickabus.service;

import hw1.pickabus.model.Reservation;
import hw1.pickabus.model.Trip;
import hw1.pickabus.repository.ReservationRepository;
import hw1.pickabus.repository.TripRepository;
import hw1.pickabus.Utils.ValidationUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    
    private final ReservationRepository reservationRepository = new ReservationRepository();

    

    public Reservation createReservation(Reservation reservation) {
        if (!ValidationUtils.isValidEmail(reservation.getPassengerEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (!ValidationUtils.isValidName(reservation.getPassengerName())) {
            throw new IllegalArgumentException("Invalid name format");
        }

        reservation.generateToken();
        logger.info("Creating reservation with token: {}", reservation.getToken());

        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> findReservationByToken(String token) {
        return reservationRepository.findByToken(token);
    }

    public List<Reservation> findReservationsByTripId(Integer tripId) {
        return reservationRepository.findByTripId(tripId);
    }
}
