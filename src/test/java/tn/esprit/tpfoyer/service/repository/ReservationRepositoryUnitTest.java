package tn.esprit.tpfoyer.service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReservationRepositoryUnitTest {

    @Mock
    private ReservationRepository reservationRepository;


    private List<Reservation> reservationList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservationList = new ArrayList<>();
        reservationList.add(new Reservation("f1", new Date(), true, null));
        reservationList.add(new Reservation("f2", new Date(), false, null));
    }

    @Test
    void testFindAllByAnneeUniversitaireBeforeAndEstValide() {
        // Arrange
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(any(Date.class), anyBoolean()))
                .thenReturn(reservationList);

        // Act
        List<Reservation> results = reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(new Date(), true);

        // Assert
        assertThat(results).hasSize(2);
        verify(reservationRepository, times(1)).findAllByAnneeUniversitaireBeforeAndEstValide(any(Date.class), anyBoolean());
    }
}
