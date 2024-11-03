package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer foyer;

    @BeforeEach
    public void setUp() {
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer A");
        foyer.setCapaciteFoyer(100L);
    }

    @Test
    public void testRetrieveAllFoyers() {
        when(foyerRepository.findAll()).thenReturn(Arrays.asList(foyer));
        List<Foyer> foyers = foyerService.retrieveAllFoyers();
        assertEquals(1, foyers.size());
        assertEquals("Foyer A", foyers.get(0).getNomFoyer());
    }

    @Test
    public void testRetrieveFoyer() {
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));
        Foyer retrievedFoyer = foyerService.retrieveFoyer(1L);
        assertNotNull(retrievedFoyer);
        assertEquals("Foyer A", retrievedFoyer.getNomFoyer());
    }

    @Test
    public void testAddFoyer() {
        when(foyerRepository.save(foyer)).thenReturn(foyer);
        Foyer savedFoyer = foyerService.addFoyer(foyer);
        assertNotNull(savedFoyer);
        assertEquals("Foyer A", savedFoyer.getNomFoyer());
    }

    @Test
    public void testModifyFoyer() {
        foyer.setNomFoyer("Updated Foyer");
        when(foyerRepository.save(foyer)).thenReturn(foyer);
        Foyer updatedFoyer = foyerService.modifyFoyer(foyer);
        assertNotNull(updatedFoyer);
        assertEquals("Updated Foyer", updatedFoyer.getNomFoyer());
    }

    @Test
    public void testRemoveFoyer() {
        doNothing().when(foyerRepository).deleteById(1L);
        foyerService.removeFoyer(1L);
        verify(foyerRepository, times(1)).deleteById(1L);
    }
}

