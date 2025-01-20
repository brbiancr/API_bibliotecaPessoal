package biancr.bibliotecaapi.controller.mapper;

import biancr.bibliotecaapi.controller.dto.CadastroLivroDTO;
import biancr.bibliotecaapi.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro toEntity(CadastroLivroDTO dto);

}
