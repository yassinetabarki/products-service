package micro.service.products.Specifications;

import jakarta.persistence.criteria.*;
import lombok.NoArgsConstructor;
import micro.service.products.Models.Product;
import micro.service.products.TDOs.ProductDTO;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class ProductSpecifications implements Specification<Product> {

    private ProductDTO search;

    public ProductSpecifications(ProductDTO search) {
        super();
        this.search = search;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        root.fetch("category", JoinType.LEFT);

        if (search.getName() != null && !search.getName().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + search.getName() + "%"));
        }
        if (search.getDescription() != null && !search.getDescription().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("description"), "%" + search.getDescription() + "%"));
        }
        if (search.getStatus() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), search.getStatus()));
        }
        if (search.getStock() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("stock"), search.getStock()));
        }
        if (search.getPrice() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("price"), search.getPrice()));
        }

        return predicate;

    }

}
