package tsdw.prova.prova_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsdw.prova.prova_rest.domain.Nazione;
import tsdw.prova.prova_rest.service.NazioneService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/nazione")
public class NazioneController {
    @Autowired
    private NazioneService nazioneService;

    @GetMapping
    public ResponseEntity<List<Nazione>> getList() {
        return ResponseEntity.ok(nazioneService.getList());
    }

    @PostMapping("/add")
    public ResponseEntity<Nazione> add(@Valid @RequestBody Nazione nazione) {
        return ResponseEntity.ok(nazioneService.add(nazione));
    }
}
