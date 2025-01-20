package biancr.bibliotecaapi.repository;

import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    Optional<Livro> findByIsbn(String isbn);

}
