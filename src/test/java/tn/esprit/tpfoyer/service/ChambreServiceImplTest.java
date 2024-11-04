package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChambreServiceImplTest {

    @Autowired
    ChambreServiceImpl chambreService;

    @Test
    public void testRetrieveAllChambres() {
        List<Chambre> chambres = chambreService.retrieveAllChambres();
        assertNotNull(chambres);
    }

    @Test
    public void testAddChambre() {
        Chambre chambre = new Chambre();
        chambre.setTypeC(TypeChambre.SIMPLE);  // Set appropriate values
        Chambre savedChambre = chambreService.addChambre(chambre);
        assertNotNull(savedChambre);
        assertEquals(chambre.getTypeC(), savedChambre.getTypeC());
    }
}
