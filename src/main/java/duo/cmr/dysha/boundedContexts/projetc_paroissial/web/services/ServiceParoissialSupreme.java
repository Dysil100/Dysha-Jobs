package duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres.DashBoardParoissial;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres.MoisActuel;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres.Top3Categorie;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres.Top3Donneur;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donateur.Donateur;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices.DonateursService;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices.DonationsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class ServiceParoissialSupreme {
    private DonationsService donationsService;
    private DonateursService donateursService;


    public DashBoardParoissial getDashBoard() {
        Long montantTotalMois = donationsService.calculerMontantTotalMois();
        String nomMois = Month.of(LocalDate.now().getMonthValue()).getDisplayName(TextStyle.FULL, Locale.FRENCH);

        // Récupération du top 3 des catégories avec la plus grande somme de donations
        List<Top3Categorie> top3Categories = donationsService.getTop3Categories();

        // Récupération du top 3 des donateurs du mois
        List<Top3Donneur> top3Donateurs = donationsService.getTop3Donateurs();

        // Récupération de toutes les donations
        List<Donation> donations = donationsService.getAllDonations().stream().filter(Donation::isValide).toList();

        // Récupération de la liste des mois disponibles pour le filtrage
        List<String> moisList = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        // Récupération de la liste des catégories pour le filtrage
        List<String> categories = donationsService.getDonationCategories();


        return new DashBoardParoissial(new MoisActuel(nomMois, montantTotalMois), top3Categories, top3Donateurs, donations, moisList, categories);
    }
}
