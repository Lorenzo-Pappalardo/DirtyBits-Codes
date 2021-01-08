package tsdw.prova.prova_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsdw.prova.prova_rest.domain.Nazione;
import tsdw.prova.prova_rest.repository.NazioneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NazioneService {
    @Autowired
    private NazioneRepository nazioneRepository;

    public List<Nazione> getList() {
        List<Nazione> tmp = new ArrayList<>();
        nazioneRepository.findAll().forEach(tmp::add);
        return tmp;
    }

    public Optional<Nazione> get(Integer id) {
        return nazioneRepository.findById(id);
    }

    public Nazione add(Nazione nazione) {
        return nazioneRepository.save(nazione);
    }
}
