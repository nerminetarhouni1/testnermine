package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j // Lombok annotation to enable logging
@RequestMapping("/chambre")
public class ChambreRestController {

    private final IChambreService chambreService;

    // http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        log.info("Request received to retrieve all chambres");
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        log.debug("Chambres retrieved: {}", listChambres);
        return listChambres;
    }

    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/{chambre-id}
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        log.info("Request received to retrieve chambre with ID: {}", chId);
        Chambre chambre = chambreService.retrieveChambre(chId);
        if (chambre == null) {
            log.warn("Chambre with ID {} not found", chId);
        } else {
            log.debug("Chambre retrieved: {}", chambre);
        }
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        log.info("Request received to add a new chambre: {}", c);
        Chambre chambre = chambreService.addChambre(c);
        log.debug("Chambre added: {}", chambre);
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        log.info("Request received to remove chambre with ID: {}", chId);
        chambreService.removeChambre(chId);
        log.info("Chambre with ID {} removed", chId);
    }

    // http://localhost:8089/tpfoyer/chambre/modify-chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        log.info("Request received to modify chambre: {}", c);
        Chambre chambre = chambreService.modifyChambre(c);
        log.debug("Chambre modified: {}", chambre);
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/trouver-chambres-selon-typ/{tc}
    @GetMapping("/trouver-chambres-selon-typ/{tc}")
    public List<Chambre> trouverChSelonTC(@PathVariable("tc") TypeChambre tc) {
        log.info("Request received to find chambres by type: {}", tc);
        List<Chambre> chambres = chambreService.recupererChambresSelonTyp(tc);
        log.debug("Chambres found by type {}: {}", tc, chambres);
        return chambres;
    }
}

