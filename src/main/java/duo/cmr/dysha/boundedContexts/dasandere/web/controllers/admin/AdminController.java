package duo.cmr.dysha.boundedContexts.dasandere.web.controllers.admin;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.dysha.boundedContexts.routen.Routen.*;


@Controller
@AllArgsConstructor
@RequestMapping(ADMINROUTE)
@AdminOnly
public class AdminController {
    private ServiceSupreme serviceSupreme;


    @GetMapping(EMPTYROUTE)
    public String adminindex(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("text", text);
        return "index";
        //return "rootindex";
    }

    @GetMapping(AllUSERS)
    public String userliste(Model model, @ModelAttribute("text") String text) {
        model.addAttribute("usersArchiv", serviceSupreme.alleUsersArchiv());
        model.addAttribute("userList", serviceSupreme.alleAppUsers());
        return "allUsers";
    }

    @GetMapping("users/enable/{id}")
    public String enableUser(@PathVariable Long id) {
        serviceSupreme.enableUserById(id);
        return "redirect:/adminindex/users/all";
    }

    @GetMapping("/users/disable/{id}")
    public String disableUser(@PathVariable Long id) {
        serviceSupreme.disableUserById(id);
        return "redirect:/adminindex/users/all";
    }

    @GetMapping("/users/block/{id}")
    public String blockUser(@PathVariable Long id) {
        serviceSupreme.blockUserById(id);
        return "redirect:/adminindex/users/all";
    }

    @GetMapping("/users/deblock/{id}")
    public String deblockUser(@PathVariable Long id) {
        serviceSupreme.deBlockUserById(id);
        return "redirect:/adminindex/users/all";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        serviceSupreme.deleteUserById(id);
        return "redirect:/adminindex/users/";
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
