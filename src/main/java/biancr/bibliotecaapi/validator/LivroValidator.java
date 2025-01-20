package biancr.bibliotecaapi.validator;

import biancr.bibliotecaapi.exceptions.RegistroDuplicadoException;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository repository;

    public void validar(Livro livro){
        if(existeLivroComIsbn(livro))
            throw new RegistroDuplicadoException("ISBN já cadastrado!");
        
    }

    private boolean existeLivroComIsbn(Livro livro) {
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null){
            return livroEncontrado.isPresent();
        }

        return livroEncontrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }
}
