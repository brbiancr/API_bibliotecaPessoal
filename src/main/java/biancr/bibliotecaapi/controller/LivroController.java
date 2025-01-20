package biancr.bibliotecaapi.controller;

import biancr.bibliotecaapi.controller.dto.CadastroLivroDTO;
import biancr.bibliotecaapi.controller.mapper.LivroMapper;
import biancr.bibliotecaapi.model.Livro;
import biancr.bibliotecaapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
