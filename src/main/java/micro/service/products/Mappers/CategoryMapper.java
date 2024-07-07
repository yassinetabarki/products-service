package micro.service.products.Mappers;

import micro.service.products.Models.Category;
import micro.service.products.TDOs.CategoryDTO;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDTO dto);
//    @Mapping(target = "productNumber" )
    CategoryDTO toDTO(Category dto);


}
