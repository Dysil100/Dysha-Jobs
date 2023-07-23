package duo.cmr.dysha.boundedContexts.projetc_paroissial.web.controllers;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.FileInfos;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.autres.DashBoardParoissial;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.ServiceParoissialSupreme;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices.DonateursService;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices.DonationsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@Controller
@AllArgsConstructor
@RequestMapping("/paroisse")
public class DonationsControlleur {
    private DonationsService donationsService;
    private DonateursService donateursService;
    private ServiceParoissialSupreme serviceParoissialSupreme;
    private AppUserService appUserService;
    private DyshaFilesService filesService;

    @GetMapping("/donations")
    public String visualiserDonations(Model model) {
        model.addAttribute("donations", donationsService.getAllDonations().stream().filter(d-> !d.isValide()).toList());
        return "paroisse/donations";
    }

    @GetMapping("/ajouter-donation")
    public String ajouterDonation(Model model) {
        model.addAttribute("donateurs", donateursService.getAllDonateurs());
        model.addAttribute("categories", donationsService.getDonationCategories());
        return "paroisse/newDonation";
    }

    @PostMapping("/sauvegarder-donation")
    public String sauvegarderDonation(Donation donation,
                                      @RequestParam(value = "photoMateriel", required = false) MultipartFile photoMateriel,
    @ModelAttribute("user") AppUser user) {
        // Implémentez la logique pour sauvegarder la donation avec éventuellement la photo du matériel
        String photoMaterielStrings = filesService.save(new FileInfos(donation.getId_donneur(), user.getId(), "Donations", "image/*"),
                photoMateriel);
        donation.setPhoto_materiel(photoMaterielStrings);
        donation.setDate_donation(LocalDateTime.now());
        donationsService.ajouterDonation(donation);
        return "redirect:/paroisse/donations";
    }

    @GetMapping("/modifier-donation")
    public String afficherPageModificationDonation(@RequestParam("id_donation") Long idDonation, Model model) {
        // TODO: 22.07.2023 Am not done yet
        Donation donation = donationsService.getDonationById(idDonation);
        model.addAttribute("donation", donation);
        model.addAttribute("donateurs", donateursService.getAllDonateurs());
        model.addAttribute("categories", donationsService.getDonationCategories());
        return "/paroisse/newDonation";
    }

    @GetMapping("/supprimer-donation")
    public String afficherPageSuppressionDonation(@RequestParam("id_donation") Long idDonation, Model model) {
        donationsService.deleteDonationById(idDonation);
        return "redirect:/paroisse/donations";
    }

    @GetMapping("/valider-donation")
    public String afficherPageValidationDonation(@RequestParam("id_donation") Long idDonation, Model model, @ModelAttribute("user") AppUser user) {
        donationsService.validateDonationById(idDonation, user.getFullName() + "(tel:" + user.getTelephone() + "/n eMail:" + user.getEmail() + ")");
        return "redirect:/paroisse/donations";
    }

    @GetMapping("/details-donation")
    public String visualiserDetailsDonation(@RequestParam("id_donation") Long idDonation, Model model) {
        Donation donation = donationsService.getDonationById(idDonation);
        model.addAttribute("donation", donation);
        return "/paroisse/donationsDetails";
    }

    @GetMapping("/dashboard")
    public String afficherDashboard(Model model) {
        DashBoardParoissial dashBoard = serviceParoissialSupreme.getDashBoard();
        model.addAttribute("board", dashBoard);
        // Calcul du montant total des donations du mois actuel et récupération du nom du mois actuel

        return "paroisse/dashbord";
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

}
