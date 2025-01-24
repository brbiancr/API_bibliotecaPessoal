package biancr.bibliotecaapi.controller.dto;

import java.util.UUID;

public record ListaPersonalizadaDTO(
        UUID id,
        String nome,
        String descricao) {
}
