package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class DyshaProfilController {
    private AppUserService appUserService;
    DyshaWorkerService dyshaWorkerService;
    DyshaFilesService dyshaFileService;

    @GetMapping("/dyshajobs/dyshaprofil")
    public String showProfil(Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);
        model.addAttribute("userHasCuriculumVitae", dyshaFileService.cVExistByEntityId(user.getWorker().getId()));
        return "dysha_jobs/dyshaprofil";
    }

    @PostMapping("/update")
    public String updateProfil(Model model, @ModelAttribute("user") AppUser user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "dysha_jobs/dyshaprofil";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Profil updated successfully.");
        return "redirect:/dyshajobs/dyshaprofil";
    }

    @ModelAttribute("role")
    String sender(Principal user) {
        return appUserService.findByEmail(user.getName()).getRole().name();
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

    @ModelAttribute("globalUser")
    GlobalAppUser currentGlobalUser(Principal user) {
        AppUser appUser = appUserService.findByEmail(user.getName());
        return new GlobalAppUser(appUser, dyshaWorkerService.findByUserId(appUser.getId()));
    }

}

