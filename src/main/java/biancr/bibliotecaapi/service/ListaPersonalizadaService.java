package biancr.bibliotecaapi.service;

import biancr.bibliotecaapi.model.ListaPersonalizada;
import biancr.bibliotecaapi.repository.ListaPersonalizadaRepository;
import biancr.bibliotecaapi.validator.ListaPersonalizadaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListaPersonalizadaService {

    private final ListaPersonalizadaRepository repository;
    private final ListaPersonalizadaValidator validator;

    public ListaPersonalizada salvar(ListaPersonalizada lista){
        validator.validar(lista);
        return repository.save(lista);
    }

    public Optional<ListaPersonalizada> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(ListaPersonalizada listaPersonalizada) {
        repository.delete(listaPersonalizada);
    }

    public void atualizar(ListaPersonalizada listaPersonalizada) throws IllegalAccessException{
        if(listaPersonalizada.getId() == null)
            throw new IllegalAccessException("Para atualizar é necessário que a lista já esteja na base de dados");

        validator.validar(listaPersonalizada);
        repository.save(listaPersonalizada);
    }

}
