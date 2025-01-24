package biancr.bibliotecaapi.controller.mapper;

import biancr.bibliotecaapi.controller.dto.CadastroLivroDTO;
import biancr.bibliotecaapi.controller.dto.ResultadoPesquisaLivroDTO;
import biancr.bibliotecaapi.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);

}
