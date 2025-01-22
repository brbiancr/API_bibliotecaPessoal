package biancr.bibliotecaapi.controller;

import biancr.bibliotecaapi.controller.dto.CadastroLivroDTO;
import biancr.bibliotecaapi.controller.dto.ResultadoPesquisaLivroDTO;
import biancr.bibliotecaapi.controller.mapper.LivroMapper;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.service.LivroService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController{

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        // Mapear dto para entidade
        Livro livro = mapper.toEntity(dto);

        // Enviar a entidade para o service validar e salvar na base
        service.salvar(livro);

        // Criar url para acesso de dados do livro
        var url = gerarHeaderLocation(livro.getId());

        // retornar código created com header location
        return ResponseEntity.created(url).build();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("isbn") String isbn){
        return service.obterPorIsbn(isbn)
                .map(livro -> {
                    var dto =mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Object> deletar(@PathVariable("isbn") String isbn){
        return service.obterPorIsbn(isbn)
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
