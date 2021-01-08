package tsdw.prova.prova_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsdw.prova.prova_rest.domain.Citta;
import tsdw.prova.prova_rest.domain.Nazione;
import tsdw.prova.prova_rest.service.CittaService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citta")
public class CittaController {
    @Autowired
    private CittaService cittaService;

    @GetMapping
    public ResponseEntity<List<Citta>> getList() {
        return ResponseEntity.ok(cittaService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Citta> add(@Valid @RequestBody Citta citta) throws URISyntaxException {
        Citta newCitta = cittaService.add(citta);
        return ResponseEntity.created(new URI("/api/citta/" + newCitta.getId())).body(newCitta);
    }

    @PutMapping("/update")
    public ResponseEntity<Citta> update(@Valid @RequestBody Citta citta) {
        Optional<Citta> tmp = cittaService.get(citta.getId());
        if (tmp.isPresent()) {
            return ResponseEntity.ok(cittaService.update(citta));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Integer id) {
        Optional<Citta> tmp = cittaService.get(id);
        if (tmp.isPresent()) {
            cittaService.delete(id);
            return ResponseEntity.ok("Città eliminata");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Citta>> getByNazione(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(cittaService.getByNazione(id));
    }
}
