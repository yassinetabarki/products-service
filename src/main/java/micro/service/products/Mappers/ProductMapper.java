package micro.service.products.Mappers;

import micro.service.products.Models.Product;
import micro.service.products.TDOs.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    Product toProduct(ProductDTO productDTO);

}
