/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.tpfoyer;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;


/**
 *
 * @author moham
 */
public class UniversityTests {
    @Test
    public void universitytestmockito()
    {
        UniversiteRepository universiterepo = Mockito.mock(UniversiteRepository.class);
        UniversiteServiceImpl universiteservice= new UniversiteServiceImpl(universiterepo);
       
        Universite universite1 = new Universite("Université 1", "123");
        Universite universite2 = new Universite("Université 2", "1234 St");
        
        List<Universite> universites = new ArrayList <>();
        universites.add(universite1);
        universites.add(universite2);
        Mockito.when(universiterepo.findAll()).thenReturn(universites);
        assertEquals(2,universiteservice.retrieveAllUniversites().size());
        assertEquals(universite1, universites.get(0), "test premiere universite ");
        assertEquals(universite2, universites.get(1), "test 2eme universite ");
        
    }
}
