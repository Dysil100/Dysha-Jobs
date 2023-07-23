package duo.cmr.dysha.boundedContexts.projetc_paroissial.persitence.donation;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.interfaces.DonationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
@Repository
@AllArgsConstructor
public class DonationRepositoryImpl implements DonationRepository {
   
    private DaoDonationRepository daoDonationRepository;
    @Override
    public List<Donation> allDonations() {
        return toDonationsList(daoDonationRepository.findAll());
    }

    @Override
    public void save(Donation donation) {
        daoDonationRepository.save(toDonationEntity(donation));
    }

    @Override
    public List<String> getCategories() {
        return List.of("Quete ordinaire", "Quete domicale", "Quete speciale", "Fete de Recole", "Quete de missions",
                "Quete en semaine", "Construction paroissiale", "project paroissiale", "Salle paroissiale", "Amenagement",
                "Oeuvre social", "Mariage", "Bapteme", "Comunion", "Autre evenement heureux", "Autre evenemnt Malheureux",
                "Deuil",  "Contrution générale pour develloper la paroisse" );
    }

    @Override
    public void deleteById(Long idDonation) {
        daoDonationRepository.deleteById(idDonation);
    }

    @Override
    public void validate(Long idDonation, String contactName) {
        daoDonationRepository.validateNowByIdAndName(LocalDateTime.now(), idDonation, contactName);
    }

    @Override
    public Long calculerMontantTOtalDuMoisActuel() {
       return donnationsForCurrentMonth().stream().filter(d-> d.getMontant() != null).mapToLong(Donation::getMontant).sum();
    }

    @Override
    public List<Donation> donnationsForCurrentMonth() {
        return toDonationsList(daoDonationRepository.findAllForCurrentMonth(LocalDate.now().withDayOfMonth(1),
                LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())));
    }

    @Override
    public Donation getDonationById(Long idDonation) {
        return toDonation(daoDonationRepository.findById(idDonation).get());
    }

    @Override
    public List<Donation> findAllById_Donneur(Long idDonateur) {
        return toDonationsList(daoDonationRepository.findAllByIdDonneur(idDonateur));
    }


    private List<Donation> toDonationsList(Iterable<DonationEntity> all) {
        List<Donation> donations = new ArrayList<>();
        all.forEach(e -> donations.add(toDonation(e)));
        return donations;
    }

    private Donation toDonation(DonationEntity e) {
        return new Donation(e.getId_donation(), e.getIdDonneur(), e.getType(), e.getCategorie(), e.getMontant(),
                e.getDescription(), e.getNom_materiel(), e.getPhoto_materiel(),
                e.getDate_donation(), e.isValide(), e.getDate_validation(), e.getUtilisateur_validation());
    }

    private DonationEntity toDonationEntity(Donation donation) {
        return new DonationEntity(donation.getId_donneur(), donation.getType(), donation.getCategorie(),
                donation.getMontant(), donation.getDescription(), donation.getNom_materiel(),
                donation.getPhoto_materiel(), donation.getDate_donation(), donation.isValide(), donation.getDate_validation(),
                donation.getUtilisateur_validation());
    }

}
