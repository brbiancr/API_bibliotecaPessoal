package biancr.bibliotecaapi.controller.mapper;

import biancr.bibliotecaapi.controller.dto.ListaPersonalizadaDTO;
import biancr.bibliotecaapi.model.ListaPersonalizada;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ListaPersonalisadaMapper {

    public abstract ListaPersonalizada toEntity(ListaPersonalizadaDTO dto);
}
