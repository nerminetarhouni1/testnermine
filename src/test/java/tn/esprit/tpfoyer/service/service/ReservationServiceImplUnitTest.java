package tn.esprit.tpfoyer.service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplUnitTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private List<Reservation> listReservations;

    @BeforeEach
    void setUp() {
        reservation = new Reservation("f1", new Date(), true, null);
        listReservations = new ArrayList<>() {
            {
                add(new Reservation("f2", new Date(), true, null));
                add(new Reservation("f3", new Date(), false, null));
            }
        };
    }

    @Test
    void testRetrieveAllReservations() {
        // Arrange
        when(reservationRepository.findAll()).thenReturn(listReservations);

        // Act
        List<Reservation> reservations = reservationService.retrieveAllReservations();

        // Assert
        assertNotNull(reservations);
        assertEquals(2, reservations.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservationNotFound() {
        // Arrange
        when(reservationRepository.findById("unknownId")).thenReturn(Optional.empty());

        // Act
        Reservation foundReservation = reservationService.retrieveReservation("unknownId");

        // Assert
        assertNull(foundReservation);
        verify(reservationRepository, times(1)).findById("unknownId");
    }


    @Test
    void testRetrieveReservation() {
        // Arrange
        when(reservationRepository.findById("f1")).thenReturn(Optional.of(reservation));

        // Act
        Reservation foundReservation = reservationService.retrieveReservation("f1");

        // Assert
        assertNotNull(foundReservation);
        assertEquals("f1", foundReservation.getIdReservation());
        verify(reservationRepository, times(1)).findById("f1");
    }

    @Test
    void testAddReservation() {
        // Arrange
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation createdReservation = reservationService.addReservation(reservation);

        // Assert
        assertNotNull(createdReservation);
        assertEquals("f1", createdReservation.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testModifyReservation() {
        // Arrange
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation modifiedReservation = reservationService.modifyReservation(reservation);

        // Assert
        assertNotNull(modifiedReservation);
        assertEquals("f1", modifiedReservation.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testTrouverResSelonDateEtStatus() {
        // Arrange
        Date date = new Date();
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, true)).thenReturn(listReservations);

        // Act
        List<Reservation> foundReservations = reservationService.trouverResSelonDateEtStatus(date, true);

        // Assert
        assertNotNull(foundReservations);
        assertEquals(2, foundReservations.size());
        verify(reservationRepository, times(1)).findAllByAnneeUniversitaireBeforeAndEstValide(date, true);
    }

    @Test
    void testRemoveReservation() {
        // Arrange
        doNothing().when(reservationRepository).deleteById("f1");

        // Act
        reservationService.removeReservation("f1");

        // Assert
        verify(reservationRepository, times(1)).deleteById("f1");
    }
}
