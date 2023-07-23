package duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices;


import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres.Top3Categorie;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres.Top3Donneur;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.interfaces.DonationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DonationsService {
    private DonationRepository donationRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.allDonations();
    }

    public void ajouterDonation(Donation donation) {
        donationRepository.save(donation);
    }

    public Donation getDonationById(Long idDonation) {
        return donationRepository.getDonationById(idDonation);
    }

    public List<String> getDonationCategories() {
        return donationRepository.getCategories();
    }

    public void deleteDonationById(Long idDonation) {
        donationRepository.deleteById(idDonation);
    }

    public void validateDonationById(Long idDonation, String contactName) {
        donationRepository.validate(idDonation, contactName);
    }

    public Long calculerMontantTotalMois() {
        return donationRepository.calculerMontantTOtalDuMoisActuel();
    }

    public List<Top3Categorie> getTop3Categories() {
        return List.of(new Top3Categorie("project paroissial", 203200L), new Top3Categorie("Evenement Heuereux", 135000L), new Top3Categorie("Assistance sociale", 200075L));
    }

    public List<Top3Donneur> getTop3Donateurs() {
        return List.of(new Top3Donneur("Wilfried Foka", 200456L, 5L), new Top3Donneur("Un particulier", 890067L, 7L),new Top3Donneur("Une autre Dame", 800067L, 8L));
    }
}
