
package tn.esprit.tpfoyer.service.controller;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.test.annotation.Rollback;
        import org.springframework.test.web.servlet.MockMvc;
        import tn.esprit.tpfoyer.entity.Reservation;
        import tn.esprit.tpfoyer.repository.ReservationRepository;

        import java.util.Date;

        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReservationRepository reservationRepository; // Inject repository for actual DB interaction

    @BeforeEach
    void setUp() {
        reservationRepository.deleteAll(); // Clean up the database before each test
    }

    @Test
    @Rollback
    void testGetReservations() throws Exception {
        // Set up test data
        reservationRepository.save(new Reservation("f1", new Date(), true, null));
        reservationRepository.save(new Reservation("f2", new Date(), false, null));

        mockMvc.perform(get("/reservation/retrieve-all-reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idReservation").value("f1"))
                .andExpect(jsonPath("$[1].idReservation").value("f2"));
    }

    @Test
    @Rollback
    void testAddReservation() throws Exception {
        Reservation reservation = new Reservation("f1", new Date(), true, null);

        mockMvc.perform(post("/reservation/add-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservation)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idReservation").value("f1"));
    }

}
