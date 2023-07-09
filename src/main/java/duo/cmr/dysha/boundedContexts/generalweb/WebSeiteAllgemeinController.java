package duo.cmr.dysha.boundedContexts.generalweb;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.Principal;

import static duo.cmr.dysha.boundedContexts.routen.Routen.*;

@Controller
@AllArgsConstructor
public class WebSeiteAllgemeinController {
    ServiceSupreme serviceSupreme;
    // TODO: 06.02.22 definiere ein Supreme Service für this.class
    @GetMapping(EMPTYROUTE)
    public String index(Model model){
        model.addAttribute("login", true);
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        AppUser user = serviceSupreme.authenticateUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/dyshajobs/jobindex";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute("error") String error){
        model.addAttribute("login", true);
        return "login";
    }

    @GetMapping("/login?error")
    public String loginError(Model model){
        model.addAttribute("login", true);
        model.addAttribute("error", true);
        return "login";
    }

    @PostMapping("/logout")
    public String handleLogout(HttpServletRequest request, HttpServletResponse response) {
        // Terminer la session de l'utilisateur
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Effacer les cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        // Rediriger vers la page de login
        return "redirect:/login";
    }

    @GetMapping(TELECHARGER)
    public String telecharger(Model model){
        return "telecharger";
    }
    // TODO: 03.02.22 implement all file related to the Web-Site so almost all may be just getMapping;

}