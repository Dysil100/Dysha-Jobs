package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.produit;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("product")
public class ProductEntity {

    @Id
    private Long id;

    private Long userId;
    private String userEmail;
    private String title;
    private String description;
    private Double price;
    private Boolean active;
    private Boolean vente;
    private String cathegorie;
    private String region;
    private String ville;
    private String quartier;
    private List<String> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductEntity(Long userId, String userEmail, String title, String description, Double price, Boolean active, boolean vente, String cathegorie, String region,
                         String ville, String quartier, List<String> images, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.active = active;
        this.vente = vente;
        this.cathegorie = cathegorie;
        this.region = region;
        this.ville = ville;
        this.quartier = quartier;
        this.images = images;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
