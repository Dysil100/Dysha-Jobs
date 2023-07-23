package duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donateur;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Donateur {
    private Long id_donneur;

    private String nom;
    private String prenom;
    private String sexe;
    private String adresse_telephonique;
    private boolean croyant;
    private boolean visiteur;
    private String institution;
    private String photo;
    private List<Donation> donations;

    public Donateur(String nom, String prenom, String sexe, String adresse_telephonique, boolean croyant, boolean visiteur, String institution, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse_telephonique = adresse_telephonique;
        this.croyant = croyant;
        this.visiteur = visiteur;
        this.institution = institution;
        this.photo = photo;
    }

    public Donateur() {
    }
}
