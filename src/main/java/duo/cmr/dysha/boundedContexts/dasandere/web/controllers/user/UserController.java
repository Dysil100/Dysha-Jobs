package duo.cmr.dysha.boundedContexts.dasandere.web.controllers.user;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.User;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

import static duo.cmr.dysha.boundedContexts.routen.Routen.USERINDEX;

@Controller
@AllArgsConstructor
@User
public class UserController {
    ServiceSupreme serviceSupreme;

    @GetMapping(USERINDEX)
    public String userindex(Model model, @ModelAttribute("user") AppUser user) {
        model.addAttribute("user", user);
        return "index";
    }

    @ModelAttribute("user")
    AppUser handle(Principal user) {
        return serviceSupreme.getUserByEmail(user.getName());
    }

    @ModelAttribute("profile")
    AppUser profile(Principal user) {
        return serviceSupreme.getUserByEmail(user.getName());
    }
}
