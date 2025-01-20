package biancr.bibliotecaapi.controller.dto;

import biancr.bibliotecaapi.model.Genero;
import biancr.bibliotecaapi.model.Status;
import biancr.bibliotecaapi.model.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;

public record CadastroLivroDTO(
        @NotBlank(message = "Campo obrigatorio!")
        @ISBN
        String isbn,
        @NotBlank(message = "Campo obrigatorio!")
        @Size(message = "Campo fora do tamanho", min = 2, max = 150)
        String titulo,
        @NotBlank(message = "Campo obrigatório!")
        @Size(message = "Campo fora do tamanho", min = 2, max = 50)
        String autor,
        @NotNull(message = "Campo obrigatorio!")
        Genero genero,
        Integer anoPublicacao,
        @NotNull(message = "Campo obrigatorio!")
        Tipo tipo,
        Status statusLeitura
) {

}
