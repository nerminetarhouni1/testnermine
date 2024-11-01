package tn.esprit.tpfoyer.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer.control.ReservationRestController;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReservationControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ReservationRestController reservationRestController;

    @Mock
    private IReservationService reservationService;

    private ObjectMapper objectMapper; // To convert objects to JSON

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationRestController).build();
        objectMapper = new ObjectMapper(); // Initialize ObjectMapper here
    }

    @Test
    void testGetReservations() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation("f1", new Date(), true, null));
        reservations.add(new Reservation("f2", new Date(), false, null));

        when(reservationService.retrieveAllReservations()).thenReturn(reservations);

        mockMvc.perform(get("/reservation/retrieve-all-reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idReservation").value("f1"))
                .andExpect(jsonPath("$[1].idReservation").value("f2"));

        verify(reservationService, times(1)).retrieveAllReservations();
    }

    @Test
    void testRetrieveReservation() throws Exception {
        Reservation reservation = new Reservation("f1", new Date(), true, null);
        when(reservationService.retrieveReservation("f1")).thenReturn(reservation);

        mockMvc.perform(get("/reservation/retrieve-reservation/f1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idReservation").value("f1"));

        verify(reservationService, times(1)).retrieveReservation("f1");
    }

    @Test
    void testAddReservation() throws Exception {
        Reservation reservation = new Reservation("f1", new Date(), true, null);
        when(reservationService.addReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(post("/reservation/add-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idReservation").value("f1"));

        verify(reservationService, times(1)).addReservation(any(Reservation.class));
    }

    @Test
    void testRemoveReservation() throws Exception {
        doNothing().when(reservationService).removeReservation("f1");

        mockMvc.perform(delete("/reservation/remove-reservation/f1"))
                .andExpect(status().isOk());

        verify(reservationService, times(1)).removeReservation("f1");
    }

    @Test
    void testModifyReservation() throws Exception {
        Reservation reservation = new Reservation("f1", new Date(), true, null);
        when(reservationService.modifyReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(put("/reservation/modify-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idReservation").value("f1"));

        verify(reservationService, times(1)).modifyReservation(any(Reservation.class));
    }

    @Test
    void testRetrieveReservationByDateAndStatus() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation("f1", new Date(), true, null));
        when(reservationService.trouverResSelonDateEtStatus(any(Date.class), anyBoolean())).thenReturn(reservations);

        mockMvc.perform(get("/reservation/retrieve-reservation-date-status/2024-10-01/true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idReservation").value("f1"));

        verify(reservationService, times(1)).trouverResSelonDateEtStatus(any(Date.class), anyBoolean());
    }
}
