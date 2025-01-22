package biancr.bibliotecaapi.controller.dto;

import biancr.bibliotecaapi.model.Genero;
import biancr.bibliotecaapi.model.Status;
import biancr.bibliotecaapi.model.Tipo;

public record ResultadoPesquisaLivroDTO(
        String isbn,
        String titulo,
        String autor,
        Genero genero,
        Integer anoPublicacao,
        Tipo tipo,
        Status statusLeitura,
        Integer avaliacao,
        String obsevacoes
) {
}
