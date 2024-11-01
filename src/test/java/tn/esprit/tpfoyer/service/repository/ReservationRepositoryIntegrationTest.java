package tn.esprit.tpfoyer.service.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use the existing database
class ReservationRepositoryIntegrationTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testFindAllReservations() {
        // Given
        Reservation reservation1 = new Reservation("f1", new Date(), true, null);
        Reservation reservation2 = new Reservation("f2", new Date(), false, null);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

        // When
        List<Reservation> reservations = reservationRepository.findAll();

        // Then
        Assertions.assertEquals(2, reservations.size());
    }

    @Test
    void testFindById() {
        // Given
        Reservation reservation = new Reservation("f1", new Date(), true, null);
        reservationRepository.save(reservation);

        // When
        Reservation foundReservation = reservationRepository.findById("f1").orElse(null);

        // Then
        Assertions.assertNotNull(foundReservation);
        Assertions.assertEquals("f1", foundReservation.getIdReservation());
    }

    @Test
    void testDeleteReservation() {
        // Given
        Reservation reservation = new Reservation("f1", new Date(), true, null);
        reservationRepository.save(reservation);

        // When
        reservationRepository.deleteById("f1");

        // Then
        Assertions.assertFalse(reservationRepository.findById("f1").isPresent());
    }

    // Add more tests as needed
}
