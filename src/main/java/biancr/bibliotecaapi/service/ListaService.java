package biancr.bibliotecaapi.service;

import biancr.bibliotecaapi.model.ListaPersonalizada;
import biancr.bibliotecaapi.repository.ListaRepository;
import biancr.bibliotecaapi.validator.ListaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListaService {

    private final ListaRepository repository;
    private final ListaValidator validator;

    public ListaPersonalizada salvar(ListaPersonalizada lista){
        validator.validar(lista);
        return repository.save(lista);
    }
}
