/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.tpfoyer;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;



/**
 *
 * @author nermine
 */

public class EtudiantTests {
    @Test
   public void etudianttestmockito()
    {
        EtudiantRepository etudiantrepo = Mockito.mock(EtudiantRepository.class);
        EtudiantServiceImpl etudiantservice= new EtudiantServiceImpl(etudiantrepo);
        Etudiant etudiant1 = new Etudiant("nermine", "t", 12345678L, new Date());
        Etudiant etudiant2 = new Etudiant("t", "nermine", 12347678L, new Date());
        
        List<Etudiant> etudiants = new ArrayList <>();
        etudiants.add(etudiant1);
        etudiants.add(etudiant2);
        Mockito.when(etudiantrepo.findAll()).thenReturn(etudiants);
        assertEquals(2,etudiantservice.retrieveAllEtudiants().size());
        assertEquals(etudiant1, etudiants.get(0), "First Etudiant ");
        assertEquals(etudiant2, etudiants.get(1), "Second Etudiant ");
        
    }
   
   
}