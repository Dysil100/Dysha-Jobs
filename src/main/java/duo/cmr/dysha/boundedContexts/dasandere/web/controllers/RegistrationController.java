package duo.cmr.dysha.boundedContexts.dasandere.web.controllers;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.RegistrationRequest;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Objects;

import static duo.cmr.dysha.boundedContexts.routen.Routen.CONFRIMREGISTRATION;
import static duo.cmr.dysha.boundedContexts.routen.Routen.REGISTRATION;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

// TODO: 02.02.22 Implement password recuperation;

    @GetMapping(REGISTRATION)
    public String registerForm(Model model, @ModelAttribute("formular") RegistrationRequest request) {
        model.addAttribute("form", request);
        model.addAttribute("registration", true);
        return "login";
    }

    @PostMapping(REGISTRATION)
    public String register(Model model, @ModelAttribute("form") RegistrationRequest request) {
        if (!registrationService.validateForm(request)) {
            model.addAttribute("error", "Seules les Lettre, les chiffres et quelques caratere speciaux sont Authorisé");
            model.addAttribute("registration", true);
            return "login";
        }
        if (!Objects.equals(request.getPassword(), request.getConfirmationPassword())){
            model.addAttribute("error", "Pasword and password confirmation doesn't match");
            model.addAttribute("registration", true);
            return "login";
        }

        request.setEmail(request.getEmail().toLowerCase(Locale.ROOT));
        String register = "Notifications: " +  registrationService.register(request);

        model.addAttribute("text", register);
        model.addAttribute("notification", true);
        return "login";
    }

    @GetMapping(CONFRIMREGISTRATION)
    public String confirm(Model model, @RequestParam("token") String token) {
        String notif = "Notifications: " + registrationService.confirmToken(token);
        model.addAttribute("text", notif);
        model.addAttribute("notification", true);
        return "login";
    }

    @ModelAttribute("formular")
    RegistrationRequest request() {
        return new RegistrationRequest(null, null, null, null, null, null);
    }
}
