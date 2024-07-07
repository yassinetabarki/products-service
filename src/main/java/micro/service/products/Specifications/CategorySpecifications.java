package micro.service.products.Specifications;

import jakarta.persistence.criteria.*;
import lombok.NoArgsConstructor;
import micro.service.products.Models.Category;
import micro.service.products.Models.Product;
import micro.service.products.TDOs.CategoryDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class CategorySpecifications implements Specification<Category> {

    private CategoryDTO search;

    public CategorySpecifications(CategoryDTO search) {
        super();
        this.search = search;
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        root.fetch("products", JoinType.LEFT);

        List<Predicate> prds = new ArrayList<>();
        if (search.getName() != null) {
            prds.add(cb.like(root.get("name"), "%" + search.getName() + "%"));
        }
        if (search.getDescription() != null) {
            prds.add(cb.like(root.get("description"), "%" + search.getDescription() + "%"));
        }
        return cb.and(prds.toArray(new Predicate[0]));
    }

    public static Specification<Category> withProductsCount() {
        return (root, query, cb) -> {
            root.fetch("products", JoinType.LEFT); // Fetch products to avoid N+1 issue

            query.distinct(true); // Ensure distinct results to avoid duplicates

            Expression<Long> categoryId = root.get("id");
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Product> productRoot = subquery.from(Product.class);
            subquery.select(cb.count(productRoot.get("id")));
            subquery.where(cb.equal(productRoot.get("category"), categoryId));

            return cb.greaterThanOrEqualTo(subquery, 0L);
        };
    }

}
