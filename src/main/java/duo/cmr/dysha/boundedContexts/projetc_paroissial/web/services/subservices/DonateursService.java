package duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donateur.Donateur;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.interfaces.DonateurRepository;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.interfaces.DonationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DonateursService {
    private DonateurRepository donateurRepository;
    private DonationRepository donationRepository;

    public void ajouterDonateur(Donateur donateur) {
        donateurRepository.save(donateur);
    }

    public List<Donateur> getAllDonateurs() {
        return donateurRepository.allDonateurs();
    }

    public Donateur getDonateurById(Long idDonateur) {
        List<Donation> allByIdDonneur = donationRepository.findAllById_Donneur(idDonateur);
        Donateur byId = donateurRepository.findById(idDonateur);
        byId.setDonations(allByIdDonneur);
        return byId;
    }
}
