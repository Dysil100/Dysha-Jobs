package duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;

import java.util.List;

public interface DonationRepository {
    List<Donation> allDonations();

    void save(Donation donation);

    List<Donation> donnationsForCurrentMonth();

    Donation getDonationById(Long idDonation);

    List<Donation> findAllById_Donneur(Long idDonateur);

    List<String> getCategories();

    void deleteById(Long idDonation);

    void validate(Long idDonation, String contactName);

    Long calculerMontantTOtalDuMoisActuel();
}
