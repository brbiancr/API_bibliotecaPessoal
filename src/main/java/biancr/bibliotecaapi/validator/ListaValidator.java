package biancr.bibliotecaapi.validator;

import biancr.bibliotecaapi.exceptions.RegistroDuplicadoException;
import biancr.bibliotecaapi.model.ListaPersonalizada;
import biancr.bibliotecaapi.repository.ListaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ListaValidator {

    private final ListaRepository repository;

    public void validar(ListaPersonalizada lista){
        if(existeListaComNome(lista))
            throw new RegistroDuplicadoException("Lista já cadastrada");
    }

    private boolean existeListaComNome(ListaPersonalizada lista) {
        Optional<ListaPersonalizada> listaEncontrada = repository.findByNome(lista.getNome());

        if(lista.getId() == null)
            return listaEncontrada.isPresent();

        return listaEncontrada
                .map(ListaPersonalizada::getId)
                .stream()
                .anyMatch(id -> !id.equals(lista.getId()));

    }
}
