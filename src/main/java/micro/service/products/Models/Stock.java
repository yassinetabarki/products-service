package micro.service.products.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {
    @Id
    @SequenceGenerator(
            name = "stock",
            sequenceName = "stock_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "stock"
    )
    private Long id;
    private Integer quantity;

}
