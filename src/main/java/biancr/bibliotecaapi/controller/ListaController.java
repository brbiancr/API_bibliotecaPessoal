package biancr.bibliotecaapi.controller;

import biancr.bibliotecaapi.controller.dto.ListaPersonalizadaDTO;
import biancr.bibliotecaapi.model.ListaPersonalizada;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listas")
@RequiredArgsConstructor
public class ListaController {

    // Salvar lista
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid ListaPersonalizadaDTO dto){
        return null;
    }

    // Deletar lista

    // Atualizar lista

    // Obter detalhes
}
