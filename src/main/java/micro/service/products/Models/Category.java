package micro.service.products.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import micro.service.products.Enums.Status;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Category {
    @Id
    @SequenceGenerator(
            name = "categories",
            sequenceName = "categories_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.UUID,
            generator = "categories"
    )
    private Long id;

    private String name;

    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;

    @Transient
    private Integer productNumber;

    public Integer getProductNumber() {
        return this.products == null ? 0 : this.products.size();
    }


}
