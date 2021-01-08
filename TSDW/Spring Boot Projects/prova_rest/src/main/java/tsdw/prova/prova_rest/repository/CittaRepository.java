package tsdw.prova.prova_rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tsdw.prova.prova_rest.domain.Citta;
import tsdw.prova.prova_rest.domain.Nazione;

@Repository
public interface CittaRepository extends CrudRepository<Citta, Integer> {
    Iterable<Citta> findByNazione_Id(Integer id);
}
