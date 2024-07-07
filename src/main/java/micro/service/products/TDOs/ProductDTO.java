package micro.service.products.TDOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.service.products.Enums.Status;
import micro.service.products.Models.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Status status;
    private Integer stock;
    private Double price;
    private Category category;
}
