package duo.cmr.dysha.boundedContexts.dasandere.web.controllers.leader;

import duo.cmr.dysha.boundedContexts.avis.web.service.AvisService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.dysha.boundedContexts.routen.Routen.LEADERROUTE;
import static duo.cmr.dysha.boundedContexts.routen.Routen.EMPTYROUTE;

@Controller
@AllArgsConstructor
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;

    @GetMapping(EMPTYROUTE)
    public String leaderindex(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("text", text);
        return "index";
        //return "rootindex";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }


    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "au Leader " + userByEmail.getFirstName();
    }

    @ModelAttribute("profile")
    AppUser profile(Principal user) {
        return serviceSupreme.getUserByEmail(user.getName());
    }
}
