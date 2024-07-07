package micro.service.products.Services;

import lombok.AllArgsConstructor;
import micro.service.products.Mappers.CategoryMapper;
import micro.service.products.Models.Category;
import micro.service.products.Repositories.CategoryRepository;
import micro.service.products.Specifications.CategorySpecifications;
import micro.service.products.Specifications.ProductSpecifications;
import micro.service.products.TDOs.CategoryDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    final private CategoryRepository repository;

    public List<CategoryDTO> getCategories(CategoryDTO filter) {
        Specification<Category> spec = new CategorySpecifications(filter);
        return repository.findAll(spec)
                .stream()
                .map(CategoryMapper.INSTANCE::toDTO)
                .toList();
    }
}
