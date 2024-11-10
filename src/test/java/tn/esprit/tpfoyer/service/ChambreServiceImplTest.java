package tn.esprit.tpfoyer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;
import java.util.Optional;

class ChambreServiceImplTest {

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @Mock
    private ChambreRepository chambreRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllChambres_ShouldReturnListOfChambres() {
        List<Chambre> chambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAll()).thenReturn(chambres);

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(chambreRepository).findAll();
    }

    @Test
    void retrieveChambre_ShouldReturnChambre_WhenIdExists() {
        Chambre chambre = new Chambre();
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.retrieveChambre(1L);

        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository).findById(1L);
    }

    @Test
    void retrieveChambre_ShouldReturnNull_WhenIdDoesNotExist() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.empty());

        Chambre result = chambreService.retrieveChambre(1L);

        assertNull(result);
        verify(chambreRepository).findById(1L);
    }

    @Test
    void addChambre_ShouldSaveAndReturnChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreService.addChambre(chambre);

        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository).save(chambre);
    }

    @Test
    void modifyChambre_ShouldUpdateAndReturnChambre() {
        Chambre chambre = new Chambre();
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);

        Chambre result = chambreService.modifyChambre(chambre);

        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository).save(chambre);
    }

    @Test
    void removeChambre_ShouldDeleteChambre_WhenIdExists() {
        Long chambreId = 1L;

        chambreService.removeChambre(chambreId);

        verify(chambreRepository).deleteById(chambreId);
    }

    @Test
    void recupererChambresSelonTyp_ShouldReturnChambresByType() {
        TypeChambre type = TypeChambre.SIMPLE; // Use an actual enum value from your project
        List<Chambre> chambres = List.of(new Chambre(), new Chambre());
        when(chambreRepository.findAllByTypeC(type)).thenReturn(chambres);

        List<Chambre> result = chambreService.recupererChambresSelonTyp(type);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(chambreRepository).findAllByTypeC(type);
    }
}
