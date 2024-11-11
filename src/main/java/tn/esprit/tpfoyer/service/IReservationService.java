package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(String reservationId);
    Reservation addReservation(Reservation r);
    void removeReservation(String reservationId);
    Reservation modifyReservation(Reservation reservation);

    // Here we will add later methods calling keywords and methods calling JPQL
    List<Reservation> trouverResSelonDateEtStatus(Date d, boolean b);
}
