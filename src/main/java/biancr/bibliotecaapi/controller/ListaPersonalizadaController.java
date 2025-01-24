package biancr.bibliotecaapi.controller;

import biancr.bibliotecaapi.controller.dto.ListaPersonalizadaDTO;
import biancr.bibliotecaapi.controller.mapper.ListaPersonalisadaMapper;
import biancr.bibliotecaapi.model.ListaPersonalizada;
import biancr.bibliotecaapi.service.ListaPersonalizadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/listas")
@RequiredArgsConstructor
public class ListaPersonalizadaController implements GenericController{

    private final ListaPersonalizadaService service;
    private final ListaPersonalisadaMapper mapper;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid ListaPersonalizadaDTO dto){
        ListaPersonalizada listaPersonalizada = mapper.toEntity(dto);

        service.salvar(listaPersonalizada);

        var url = gerarHeaderLocation(listaPersonalizada.getId());

        return ResponseEntity.created(url).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id))
                .map(listaPersonalizada -> {
                    service.deletar(listaPersonalizada);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar lista
    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody @Valid ListaPersonalizadaDTO dto) throws IllegalAccessException{
        return service.obterPorId(UUID.fromString(id))
                .map(listaPersonalizada -> {
                    ListaPersonalizada listaEntidade = mapper.toEntity(dto);

                    listaPersonalizada.setNome(listaEntidade.getNome());
                    listaPersonalizada.setDescricao(listaEntidade.getDescricao());

                    try{
                        service.atualizar(listaPersonalizada);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("{id}/nome")
    public ResponseEntity<Object> atualizarNome(@PathVariable("id") String id, @RequestParam("nome") String nome) throws IllegalAccessException{
        return service.obterPorId(UUID.fromString(id))
                .map(listaPersonalizada -> {
                    listaPersonalizada.setNome(nome);

                    try{
                        service.atualizar(listaPersonalizada);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obter detalhes
}
