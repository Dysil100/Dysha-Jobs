package duo.cmr.dysha.boundedContexts.projetc_paroissial.web.controllers;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.FileInfos;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donateur.Donateur;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices.DonateursService;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.subservices.DonationsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/paroisse")
public class DonateurControlleur {
    private DonateursService donateursService;
    private AppUserService appUserService;
    private DyshaFilesService filesService;

    @GetMapping("/ajouter-donneur")
    public String ajouterDonateur() {
        return "paroisse/newDonateur";
    }

    @PostMapping("/sauvegarder-donateur")
public String sauvegarderDonateur(Donateur donateur, @RequestParam("photoDonateur") MultipartFile photo, @ModelAttribute("user") AppUser user) {
        // Implémentez la logique pour sauvegarder le donateur avec sa photo
        String donateurPhotoName = filesService.save(new FileInfos(user.getId(), user.getId(), "Donateur", "image/*"), photo);
        donateur.setPhoto(donateurPhotoName);
        donateursService.ajouterDonateur(donateur);
        return "redirect:/paroisse/ajouter-donation";
    }

    @GetMapping("/donateurs")
    public String afficherTousLesDonateurs(Model model) {
        List<Donateur> donateurs = donateursService.getAllDonateurs();
        model.addAttribute("donateurs", donateurs);
        return "paroisse/donateurs";
    }

    @GetMapping("/donateurs/{idDonateur}")
    public String afficherDetailsDonateur(@PathVariable("idDonateur") Long idDonateur, Model model) {
        Donateur donateur = donateursService.getDonateurById(idDonateur);
        model.addAttribute("donateur", donateur);
        return "paroisse/donateurDetails";
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

}
