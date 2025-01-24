package biancr.bibliotecaapi.repository;

import biancr.bibliotecaapi.model.ListaPersonalizada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ListaPersonalizadaRepository extends JpaRepository<ListaPersonalizada, UUID> {

    Optional<ListaPersonalizada> findByNome(String nome);
}
