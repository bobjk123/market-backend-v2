package com.tecnm.merida.market_backend_v2.persistence.mapper;

import com.tecnm.merida.market_backend_v2.domain.Category;
import com.tecnm.merida.market_backend_v2.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "catagoryID"),
            @Mapping(source = "descripcion", target = "catagory"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Category toCategoria(Category category);
}
