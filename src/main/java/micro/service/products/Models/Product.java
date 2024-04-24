package micro.service.products.Models;

import jakarta.persistence.*;
import lombok.*;
import micro.service.products.Enums.Status;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @SequenceGenerator(
            name = "products",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "products"
    )
    private Long id;
    private String name;
    private String description;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private Integer stock;
    private Double price;

}
