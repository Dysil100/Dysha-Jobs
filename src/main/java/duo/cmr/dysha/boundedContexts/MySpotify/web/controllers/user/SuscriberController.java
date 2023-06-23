package duo.cmr.dysha.boundedContexts.MySpotify.web.controllers.user;

import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.dysha.boundedContexts.MySpotify.domain.subscriber.DyshaSubscriber;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices.DyshaSuscriberService;
import io.micronaut.http.annotation.Controller;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class SuscriberController {
    private DyshaSuscriberService suscriberService;
    private AppUserService appUserService;
    @PostMapping("/subscribemusic/{authorid}/subscribe")
    public String subscribeToChannel(@PathVariable("authorid") Long authorId, @ModelAttribute("user") AppUser user) {
        suscriberService.saveAbo(new DyshaSubscriber(user.getId(), authorId));
        return "redirect:/getmusic/{musicId}";
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

}
