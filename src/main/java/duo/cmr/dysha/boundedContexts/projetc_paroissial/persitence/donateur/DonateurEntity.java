package duo.cmr.dysha.boundedContexts.projetc_paroissial.persitence.donateur;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Table("donateurs")
public class DonateurEntity {

    @Id
    private Long id_donneur;

    private String nom;
    private String prenom;
    private String sexe;
    private String adresse_telephonique;
    private boolean croyant;
    private boolean visiteur;
    private String institution;
    private String photo;

    public DonateurEntity(String nom, String prenom, String sexe, String adresse_telephonique, boolean croyant, boolean visiteur, String institution, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse_telephonique = adresse_telephonique;
        this.croyant = croyant;
        this.visiteur = visiteur;
        this.institution = institution;
        this.photo = photo;
    }
}
