package tn.esprit.tpfoyer.service.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ReservationServiceImplIntegrationTest {

    @Autowired
    IReservationService reservationService;

    @Test
    @Rollback
    @Order(1)
     void testRetrieveAllReservations() {
        List<Reservation> lisReservations = reservationService.retrieveAllReservations();
        //Assertions.assertEquals(1,lisReservations.size());
    }

    @Test
    @Rollback
    @Order(2)
    void testAddReservation() {
        Reservation reservation = new Reservation("f1", new Date(), true, null);
        reservationService.addReservation(reservation);

        List<Reservation> reservations = reservationService.retrieveAllReservations();
        //Assertions.assertEquals(1, reservations.size());
        Assertions.assertEquals("f1", reservations.get(0).getIdReservation());
    }

}
