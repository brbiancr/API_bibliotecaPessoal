package biancr.bibliotecaapi.controller;

import biancr.bibliotecaapi.controller.dto.CadastroLivroDTO;
import biancr.bibliotecaapi.controller.dto.ResultadoPesquisaLivroDTO;
import biancr.bibliotecaapi.controller.mapper.LivroMapper;
import biancr.bibliotecaapi.model.Genero;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.model.Status;
import biancr.bibliotecaapi.model.Tipo;
import biancr.bibliotecaapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
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
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("isbn") String isbn) {
        return service.obterPorIsbn(isbn)
                .map(livro -> {
                    var dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Object> deletar(@PathVariable("isbn") String isbn) {
        return service.obterPorIsbn(isbn)
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<ResultadoPesquisaLivroDTO>> pesquisar(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "titulo", required = false)
            String titulo,
            @RequestParam(value = "autor", required = false)
            String autor,
            @RequestParam(value = "genero", required = false)
            Genero genero,
            @RequestParam(value = "ano_publicacao", required = false)
            Integer anoPublicacao,
            @RequestParam(value = "tipo", required = false)
            Tipo tipo,
            @RequestParam(value = "status_leitura", required = false)
            Status statusLeitura,
            @RequestParam(value = "avaliacao", required = false)
            Integer avaliacao,
            // Para busca paginada
            @RequestParam(value = "pagina", defaultValue = "0")
            Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "5")
            Integer tamanhoPagina
    ) {
        Page<Livro> paginaResultado = service.pesquisar(isbn, titulo, autor, genero, anoPublicacao, tipo, statusLeitura, avaliacao, pagina, tamanhoPagina);

        Page<ResultadoPesquisaLivroDTO> resultado = paginaResultado.map(mapper::toDTO);

        return ResponseEntity.ok(resultado);
    }


    @PutMapping("{isbn}/atualizar")
    public ResponseEntity<Object> atualizar(@PathVariable("isbn") String isbn, @RequestBody @Valid CadastroLivroDTO dto){
        return service.obterPorIsbn(isbn)
                .map(livro -> {
                    Livro entidade = mapper.toEntity(dto);

                    livro.setIsbn(entidade.getIsbn());
                    livro.setAutor(entidade.getAutor());
                    livro.setTitulo(entidade.getTitulo());
                    livro.setTipo(entidade.getTipo());
                    livro.setGenero(entidade.getGenero());
                    livro.setStatusLeitura(entidade.getStatusLeitura());
                    livro.setAnoPublicacao(entidade.getAnoPublicacao());

                    try {
                        service.atualizar(livro);
                    } catch(IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{isbn}/avaliar")
    public ResponseEntity<Object> avaliarLivro(@PathVariable("isbn") String isbn, @RequestParam("avaliacao") Integer avaliacao ){
        return service.obterPorIsbn(isbn)
                .map(livro -> {
                    livro.setAvaliacao(avaliacao);

                    try {
                        service.atualizarAvaliacao(livro);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{isbn}/status")
    public ResponseEntity<Object> atualizarStatusLeitura(@PathVariable("isbn") String isbn, @RequestParam("statusLeitura") Status statusLeitura ){
        return service.obterPorIsbn(isbn)
                .map(livro -> {
                    livro.setStatusLeitura(statusLeitura);

                    try {
                        service.atualizarStatusLeitura(livro);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
