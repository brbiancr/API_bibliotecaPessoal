package biancr.bibliotecaapi.service;

import biancr.bibliotecaapi.model.Genero;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.model.Status;
import biancr.bibliotecaapi.model.Tipo;
import biancr.bibliotecaapi.repository.LivroRepository;
import biancr.bibliotecaapi.repository.specs.LivroSpecs;
import biancr.bibliotecaapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroValidator validator;

    public Livro salvar(Livro livro){
        validator.validar(livro);
        return repository.save(livro);
    }

    public Optional<Livro> obterPorIsbn(String isbn){
        return repository.findByIsbn(isbn);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public Page<Livro> pesquisar(
            String isbn,
            String titulo,
            String autor,
            Genero genero,
            Integer anoPublicacao,
            Tipo tipo,
            Status statusLeitura,
            Integer avaliacao,
            Integer pagina,
            Integer tamanhoPagina) {

        // select * from livro where 0 = 0
        Specification<Livro> specs = Specification
                .where(((root, query, criteriaBuilder) ->  criteriaBuilder.conjunction()));

        if(isbn != null)
            // query = query and isbn = :isbn
            specs = specs.and(LivroSpecs.isbnEqual(isbn));

        if(titulo != null)
            specs = specs.and(LivroSpecs.tituloLike(titulo));

        if(autor != null)
            specs = specs.and(LivroSpecs.autorLike(autor));

        if(genero != null)
            specs = specs.and(LivroSpecs.generoEqual(genero));

        if(anoPublicacao != null)
            specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));

        if(tipo != null)
            specs = specs.and(LivroSpecs.tipoEqual(tipo));

        if(statusLeitura != null)
            specs = specs.and(LivroSpecs.statusLeituraEqual(statusLeitura));

        if(avaliacao != null)
            specs = specs.and(LivroSpecs.avaliacaoEqual(avaliacao));

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageRequest);
    }

    public void atualizar(Livro livro) throws IllegalAccessException{
        if(livro.getId() == null){
            throw new IllegalAccessException("Para atualizar é necessário que o livro já esteja salvo na base de dados");
        }

        validator.validar(livro);
        repository.save(livro);
    }
}
