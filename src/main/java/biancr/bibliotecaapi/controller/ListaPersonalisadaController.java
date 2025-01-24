package biancr.bibliotecaapi.controller;

import biancr.bibliotecaapi.controller.dto.ListaPersonalizadaDTO;
import biancr.bibliotecaapi.controller.mapper.ListaPersonalisadaMapper;
import biancr.bibliotecaapi.model.ListaPersonalizada;
import biancr.bibliotecaapi.service.ListaPersonalisadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listas")
@RequiredArgsConstructor
public class ListaPersonalisadaController implements GenericController{

    private final ListaPersonalisadaService service;
    private final ListaPersonalisadaMapper mapper;

    // Salvar lista
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid ListaPersonalizadaDTO dto){
        ListaPersonalizada listaPersonalizada = mapper.toEntity(dto);

        service.salvar(listaPersonalizada);

        var url = gerarHeaderLocation(listaPersonalizada.getId());

        return ResponseEntity.created(url).build();
    }

    // Deletar lista

    // Atualizar lista

    // Obter detalhes
}
