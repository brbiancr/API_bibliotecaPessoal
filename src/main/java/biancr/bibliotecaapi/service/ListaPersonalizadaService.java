package biancr.bibliotecaapi.service;

import biancr.bibliotecaapi.controller.dto.ListaPersonalizadaDTO;
import biancr.bibliotecaapi.exceptions.EntidadeNaoEncontradaException;
import biancr.bibliotecaapi.exceptions.RegistroDuplicadoException;
import biancr.bibliotecaapi.model.ListaPersonalizada;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.repository.ListaPersonalizadaRepository;
import biancr.bibliotecaapi.repository.LivroRepository;
import biancr.bibliotecaapi.validator.ListaPersonalizadaValidator;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaPersonalizadaService {

    private final ListaPersonalizadaRepository repository;
    private final ListaPersonalizadaValidator validator;
    private final LivroRepository livroRepository;

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

    public void adicionarLivro(String id, String isbn){
        ListaPersonalizada listaPersonalizada = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lista não encontrada."));

        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro não encontrado."));

        if(listaPersonalizada.getLivros().contains(livro))
            throw new RegistroDuplicadoException("Livro já adicionado a lista.");

        listaPersonalizada.getLivros().add(livro);
        repository.save(listaPersonalizada);
    }

    public void removerLivro(String id, String isbn){
        ListaPersonalizada listaPersonalizada = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lista não encontrada."));

        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Livro não encontrado."));

        listaPersonalizada.getLivros().remove(livro);
        repository.save(listaPersonalizada);
    }

    public List<ListaPersonalizadaDTO> listarTodas(){
        List<ListaPersonalizada> listas = repository.findAll();

        return listas.stream()
                .map(lista ->
                        new ListaPersonalizadaDTO(lista.getNome(), lista.getDescricao())
                ).collect(Collectors.toList());
    }

}
