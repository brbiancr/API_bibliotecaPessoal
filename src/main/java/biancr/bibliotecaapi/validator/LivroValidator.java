package biancr.bibliotecaapi.validator;

import biancr.bibliotecaapi.exceptions.CampoInvalidoException;
import biancr.bibliotecaapi.exceptions.RegistroDuplicadoException;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.model.Status;
import biancr.bibliotecaapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private final LivroRepository repository;

    public void validar(Livro livro){
        if(existeLivroComIsbn(livro))
            throw new RegistroDuplicadoException("ISBN já cadastrado!");

    }

    public void validarAvaliacao(Livro livro){
        if(!avaliacaoNoLimite(livro)){
            throw new CampoInvalidoException("avaliacao", "Avaliação deve estar entre 0 e 5");
        }
    }

    public void validarStatusLeitura(Livro livro){
        if(statusInvalido(livro)){
            throw new CampoInvalidoException("statusLeitura", "O staus de leitura deve ser LIDO, LENDO, QUERO_LER");
        }
    }

    private boolean statusInvalido(Livro livro) {
        return !EnumSet.of(Status.LIDO, Status.LENDO, Status.QUERO_LER).contains(livro.getStatusLeitura());
    }

    private boolean avaliacaoNoLimite(Livro livro) {
        return livro.getAvaliacao() >=0 && livro.getAvaliacao() <= 5;
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
