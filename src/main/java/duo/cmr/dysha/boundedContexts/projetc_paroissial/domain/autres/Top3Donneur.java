package duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Top3Donneur {
    private String fullName;
    private Long  sommeTotal;
    private Long  nombreDonations;
}
