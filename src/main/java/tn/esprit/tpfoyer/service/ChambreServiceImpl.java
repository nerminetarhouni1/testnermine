package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        log.info("In method retrieveAllChambres");
        List<Chambre> listC = chambreRepository.findAll();
        log.debug("Chambres retrieved: {}", listC);
        log.info("Out of retrieveAllChambres");
        return listC;
    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        log.info("In method retrieveChambre with ID: {}", chambreId);
        Optional<Chambre> chambre = chambreRepository.findById(chambreId);
        if (chambre.isPresent()) {
            log.debug("Chambre found: {}", chambre.get());
        } else {
            log.warn("No chambre found with ID: {}", chambreId);
        }
        return chambre.orElse(null);
    }

    @Override
    public Chambre addChambre(Chambre c) {
        log.info("In method addChambre with chambre: {}", c);
        Chambre savedChambre = chambreRepository.save(c);
        log.debug("Chambre added: {}", savedChambre);
        return savedChambre;
    }

    @Override
    public Chambre modifyChambre(Chambre c) {
        log.info("In method modifyChambre with chambre: {}", c);
        Chambre updatedChambre = chambreRepository.save(c);
        log.debug("Chambre modified: {}", updatedChambre);
        return updatedChambre;
    }

    @Override
    public void removeChambre(Long chambreId) {
        log.info("In method removeChambre with ID: {}", chambreId);
        chambreRepository.deleteById(chambreId);
        log.info("Chambre with ID {} removed", chambreId);
    }

    @Override
    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc) {
        log.info("In method recupererChambresSelonTyp with type: {}", tc);
        List<Chambre> chambres = chambreRepository.findAllByTypeC(tc);
        log.debug("Chambres found by type {}: {}", tc, chambres);
        return chambres;
    }
}

