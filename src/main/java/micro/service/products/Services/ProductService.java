package micro.service.products.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import micro.service.products.Mappers.ProductMapper;
import micro.service.products.Models.Product;
import micro.service.products.Repositories.ProductRepository;
import micro.service.products.Specifications.ProductSpecifications;
import micro.service.products.TDOs.ProductDTO;
import micro.service.products.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<ProductDTO> getProducts(ProductDTO productDTO) {
        Specification<Product> spec = new ProductSpecifications(productDTO);
        return repository.findAll(spec)
                .stream()
                .map(ProductMapper.INSTANCE::toDTO)
                .toList();
    }

    public ProductDTO addProduct(ProductDTO product) {
        Product product1 = repository.save(ProductMapper.INSTANCE.toProduct(product));
        ProductDTO result = ProductMapper.INSTANCE.toDTO(product1);
        return result;
    }

    @Transactional
    public ProductDTO updateProduct(Long productId, ProductDTO payload) {
        Optional<Product> productExist = repository.findById(productId);
        if (productExist.isEmpty()) {
            throw new IllegalStateException("product not found ");
        }

        Product product = productExist.get();
        Field[] fields = payload.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(payload);
                if (value != null) {
                    Field productField = product.getClass().getDeclaredField(field.getName());
                    productField.setAccessible(true);
                    productField.set(product, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions
            }
        }
//        product.setName(payload.getName());
//        product.setDescription(payload.getDescription());
//        product.setStatus(payload.getStatus());
//        product.setStock(payload.getStock());
//        product.setPrice(payload.getPrice());
        return ProductMapper.INSTANCE.toDTO(product);
    }

    public ProductDTO getProduct(Long productId) {
        Optional<Product> product = repository.findById(productId);
        if (product.isPresent()) {
            return ProductMapper.INSTANCE.toDTO(product.get());
        } else {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    public void destroyProduct(Long productId) {
        repository.deleteById(productId);

    }
}
