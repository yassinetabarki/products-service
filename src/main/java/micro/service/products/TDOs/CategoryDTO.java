package micro.service.products.TDOs;

import lombok.*;
import micro.service.products.Enums.Status;
import micro.service.products.Models.Product;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    private Status status;
//    private List<Product> products;
    private Integer productNumber;
}
