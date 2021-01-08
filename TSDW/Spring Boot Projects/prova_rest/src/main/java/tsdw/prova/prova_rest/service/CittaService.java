package tsdw.prova.prova_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tsdw.prova.prova_rest.domain.Citta;
import tsdw.prova.prova_rest.domain.Nazione;
import tsdw.prova.prova_rest.repository.CittaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CittaService {
    @Autowired
    private CittaRepository cittaRepository;

    public Citta add(Citta citta) {
        return cittaRepository.save(citta);
    }

    public Citta update(Citta citta) {
        return cittaRepository.save(citta);
    }

    public void delete(Integer id) {
        cittaRepository.deleteById(id);
    }

    public Optional<Citta> get(Integer id) {
        return cittaRepository.findById(id);
    }

    public List<Citta> getAll() {
        List<Citta> tmp = new ArrayList<>();
        cittaRepository.findAll().forEach(tmp::add);
        return tmp;
    }

    public List<Citta> getByNazione(Integer id) {
        List<Citta> tmp = new ArrayList<>();
        cittaRepository.findByNazione_Id(id).forEach(tmp::add);
        return tmp;
    }
}
