package duo.cmr.dysha.boundedContexts.GoodDeals.domain.oders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class FilterForm {
    private String fcategorie;
    private Double prixMin;
    private Double prixMax;
    private String fregion;
    private String fville;
    private boolean fvente;

    public FilterForm() {
    }
}
