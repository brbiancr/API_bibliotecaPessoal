package biancr.bibliotecaapi.service;

import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.repository.LivroRepository;
import biancr.bibliotecaapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final LivroValidator validator;

    public Livro salvar(Livro livro){
        validator.validar(livro);
        return repository.save(livro);
    }
}
