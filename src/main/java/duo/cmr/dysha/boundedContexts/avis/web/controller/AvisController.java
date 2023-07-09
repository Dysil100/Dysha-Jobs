package duo.cmr.dysha.boundedContexts.avis.web.controller;

import duo.cmr.dysha.boundedContexts.avis.forms.Avis;
import duo.cmr.dysha.boundedContexts.avis.forms.FormAvis;
import duo.cmr.dysha.boundedContexts.avis.web.service.AvisService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

import static duo.cmr.dysha.boundedContexts.routen.Routen.AVIS;
import static duo.cmr.dysha.boundedContexts.routen.Routen.CONTACTS;

@AllArgsConstructor
@Controller
public class AvisController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;

    @GetMapping(AVIS)
    public String avis(Model model, @ModelAttribute("formAvis") FormAvis form) {
        model.addAttribute("formavis", form);
        return "avis";
    }

    @PostMapping(AVIS)
    public String avisPost(Model model, @ModelAttribute("formavis") FormAvis form, @ModelAttribute("email") String email) {
        form.setEmail(email);
        avisService.save(form.toAvis());
        return "redirect:" + AVIS;
    }

    @PostMapping("/sendCommentaire")
    public String sendMail(@RequestParam("comment") String comments, @ModelAttribute("user") AppUser user) {
        avisService.save(new Avis(user.getTelephone(), user.getEmail(), comments, LocalDateTime.now()));
        // Redirige vers une page de confirmation ou autre
        return "redirect:/contacts";
    }

    @GetMapping(CONTACTS)
    public String contacts(Model model, @ModelAttribute("user") AppUser currentUser){
        model.addAttribute("user", currentUser);
        model.addAttribute("listeAvis", avisService.alle());
        return "contacts";
    }

    @ModelAttribute("user")
    AppUser profile(Principal user) {
        String name = user.getName();
        return serviceSupreme.getUserByEmail(name);
    }

    @ModelAttribute("formAvis")
    FormAvis formavis() {
        return new FormAvis(null, null, null);
    }

}
