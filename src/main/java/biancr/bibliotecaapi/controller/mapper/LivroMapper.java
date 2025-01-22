package biancr.bibliotecaapi.controller.mapper;

import biancr.bibliotecaapi.controller.dto.CadastroLivroDTO;
import biancr.bibliotecaapi.controller.dto.ResultadoPesquisaLivroDTO;
import biancr.bibliotecaapi.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro toEntity(CadastroLivroDTO dto);

    ResultadoPesquisaLivroDTO toDTO(Livro livro);

}
