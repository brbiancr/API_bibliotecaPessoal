package biancr.bibliotecaapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ListaPersonalizadaDTO(
        @NotBlank(message = "Campo obrigatorio!")
        @Size(message = "Campo fora do tamanho", min = 2, max = 20)
        String nome,
        @Size(message = "Campo fora do tamanho", min = 2, max = 200)
        String descricao) {
}
