package duo.cmr.dysha.boundedContexts.GoodDeals.web.controllers.User;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.discussion.Discussion;
import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.Product;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices.DiscussionService;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices.ProductService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

// TODO: 04.05.2023 remplacer l'utilisation des email par les id en implementation interne

import java.security.Principal;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Controller
public class DiscussionController {

    private DiscussionService discussionService;
    private AppUserService appUserService;
    private ServiceSupreme serviceSupreme;
    private ProductService productService;

    @GetMapping("/discussions")
    public String disscussions(Model model, @ModelAttribute("sender") String sender) {
        model.addAttribute("discussions", discussionService.loadDiscussionsFor(sender));
        return "good-deals/discussions";
    }

    @GetMapping("/discussions/{id}")
    public String productDetails(@PathVariable("id") String discussionHash, @ModelAttribute("sender") String sender, Model model) {
        setUpMessage(model, sender, discussionHash);
        return "good-deals/chatpage";
    }

    @GetMapping("/contact/{productId}")
    public String chat(@PathVariable("productId") Long productId, Model model, @ModelAttribute("sender") String sender,
                       @ModelAttribute("receiverName") String receiverName) {
        Product productById = productService.getProductById(productId);
        String discussionHash = serviceSupreme.getChatDiscussionHashFor(sender, productById.getUserEmail());
        setUpMessage(model, sender, discussionHash);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        model.addAttribute("sujet", "  A propos de: " + productById.getTitre() + "  à " + productById.getRegion() +  " publié le " + productById.getCreatedAt().format(dateFormat) );
        return "good-deals/chatpage";
    }

    private void setUpMessage(Model model, String sender, String discussionHash) {
        Discussion discussion = serviceSupreme.getDiscussion(discussionHash);
        model.addAttribute("messages", discussion.getMessages());
        model.addAttribute("sender", sender);
        model.addAttribute("receiverName", receiverName(sender, discussion));
        model.addAttribute("receiver", discussion.getCurrentReceiver(sender));
    }

    @ModelAttribute("sender")
    String sender(Principal user) {
        return user.getName();
    }

    String receiverName(@ModelAttribute("sender") String sender, Discussion discussion) {
        AppUser appUser = appUserService.findByEmail(discussion.getCurrentReceiver(sender));
        return appUser.getFullName();
    }

}
