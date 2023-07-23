package duo.cmr.dysha.boundedContexts.projetc_paroissial.persitence.donation;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Table("donations")
public class DonationEntity {

    @Id
    private Long id_donation;

    private Long idDonneur;
    private String type;
    private String categorie;
    private Long montant;
    private String description;
    private String nom_materiel;
    private String photo_materiel;
    private LocalDateTime date_donation;
    private boolean valide;
    private LocalDateTime date_validation;
    private String utilisateur_validation;

    public DonationEntity(Long idDonneur, String type, String categorie, Long montant, String description,
                          String nom_materiel, String photo_materiel, LocalDateTime date_donation,
                          boolean valide, LocalDateTime date_validation, String utilisateur_validation) {
        this.idDonneur = idDonneur;
        this.type = type;
        this.categorie = categorie;
        this.montant = montant;
        this.description = description;
        this.nom_materiel = nom_materiel;
        this.photo_materiel = photo_materiel;
        this.date_donation = date_donation;
        this.valide = valide;
        this.date_validation = date_validation;
        this.utilisateur_validation = utilisateur_validation;
    }
}
