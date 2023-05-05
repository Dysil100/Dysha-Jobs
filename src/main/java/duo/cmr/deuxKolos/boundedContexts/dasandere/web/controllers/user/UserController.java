package duo.cmr.deuxKolos.boundedContexts.dasandere.web.controllers.user;

import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.deuxKolos.boundedContexts.dasandere.persistence.annotations.User;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

import static duo.cmr.deuxKolos.boundedContexts.routen.Routen.USERINDEX;

@Controller
@AllArgsConstructor
@User
public class UserController {
    ServiceSupreme serviceSupreme;

    @GetMapping(USERINDEX)
    public String userindex(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("text", text);
        model.addAttribute("role", "user");
        return "index";
    }

    @GetMapping("/goodeals/profil")
    public String userprofil(Model model, @ModelAttribute("text") String text, @ModelAttribute("profile") AppUser currentUser) {
        model.addAttribute("text", text);
        model.addAttribute("role", "user");
        model.addAttribute("profile", currentUser);
        return "gooddealsprofil";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return userByEmail.getFirstName();
    }

    @ModelAttribute("profile")
    AppUser profile(Principal user) {
        return serviceSupreme.getUserByEmail(user.getName());
    }
}
