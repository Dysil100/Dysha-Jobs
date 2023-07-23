package duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class DashBoardParoissial {
    private MoisActuel moisActuel;
    private List<Top3Categorie> top3CategorieList;
    private List<Top3Donneur> top3DonneurList;
    private List<Donation> donations;
    private List<String> moisList;
    private List<String> categories;

    public DashBoardParoissial() {
    }
}
