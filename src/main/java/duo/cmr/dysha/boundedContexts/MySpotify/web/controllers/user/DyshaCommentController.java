package duo.cmr.dysha.boundedContexts.MySpotify.web.controllers.user;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.DyshaCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class DyshaCommentController {
    private AppUserService appUserService;
    private DyshaCommentService commentService;

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }
}
