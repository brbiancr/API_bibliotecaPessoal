package biancr.bibliotecaapi.service;

import biancr.bibliotecaapi.model.ListaPersonalizada;
import biancr.bibliotecaapi.repository.ListaPersonalizadaRepository;
import biancr.bibliotecaapi.validator.ListaPersonalisadaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListaPersonalisadaService {

    private final ListaPersonalizadaRepository repository;
    private final ListaPersonalisadaValidator validator;

    public ListaPersonalizada salvar(ListaPersonalizada lista){
        validator.validar(lista);
        return repository.save(lista);
    }
}
