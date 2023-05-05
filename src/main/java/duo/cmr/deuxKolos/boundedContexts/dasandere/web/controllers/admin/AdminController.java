package duo.cmr.deuxKolos.boundedContexts.dasandere.web.controllers.admin;

import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.deuxKolos.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.deuxKolos.boundedContexts.routen.Routen.*;


@Controller
@AllArgsConstructor
@RequestMapping(ADMINROUTE)
@AdminOnly
public class AdminController {
    private ServiceSupreme serviceSupreme;

    @GetMapping("/goodeals/profil")
    public String userprofil(Model model, @ModelAttribute("text") String text, @ModelAttribute("profile") AppUser currentUser) {
        model.addAttribute("text", text);
        model.addAttribute("role", "user");
        model.addAttribute("profile", currentUser);
        return "gooddealsprofil";
    }

    @GetMapping(EMPTYROUTE)
    public String adminindex(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("text", text);
        return "index";
        //return "rootindex";
    }

    @GetMapping(USERLISTE)
    public String userliste(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("alle", serviceSupreme.alleUsersArchiv());
        return "userliste";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "L'administrateur " + userByEmail.getFirstName();
    }

    @ModelAttribute("profile")
    AppUser profile(Principal user) {
        return serviceSupreme.getUserByEmail(user.getName());
    }

}
