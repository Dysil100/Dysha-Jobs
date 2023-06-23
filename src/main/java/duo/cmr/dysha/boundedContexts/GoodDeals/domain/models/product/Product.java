package duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {

    private Long id;
    private Long userId;
    private String userEmail;
    private String titre;
    private String description;
    private double prix;
    private Boolean active;
    private Boolean vente;
    private String cathegorie;
    private String region;
    private String ville;
    private String quartier;
    private List<String> images;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(Long userId, String userEmail, String titre, String description, double prix, boolean active, boolean vente, String cathegorie, String region, String ville,
                   String quartier, List<String> images, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrix(), getPrix()) == 0 && getId().equals(product.getId())
                && Objects.equals(getUserEmail(), product.getUserEmail()) && Objects.equals(getTitre(), product.getTitre())
                && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getRegion(), product.getRegion())
                && Objects.equals(getQuartier(), product.getQuartier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
