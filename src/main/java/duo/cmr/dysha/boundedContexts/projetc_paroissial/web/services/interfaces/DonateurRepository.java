package duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donateur.Donateur;

import java.util.List;

public interface DonateurRepository {
    void save(Donateur donateur);

    List<Donateur> allDonateurs();

    Donateur findById(Long idDonateur);
}
