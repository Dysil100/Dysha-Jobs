package duo.cmr.dysha.boundedContexts.GoodDeals.web.controllers.User;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.chatmessage.ChatMessage;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices.ChatMessageService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
public class ChatController {

    private AppUserService appUserService;
    private ChatMessageService chatMessageService;
    private ServiceSupreme serviceSupreme;

    @PostMapping("/chatpage/send")
    public String send(Model model, @ModelAttribute("sender") String sender, @RequestParam String receiver, @RequestParam String sujet, @RequestParam String message) {
        ChatMessage msg = new ChatMessage(sender, receiver, sujet, message, serviceSupreme.getChatDiscussionHashFor(sender, receiver));
        chatMessageService.save(msg);
         return "redirect:/discussions/" + msg.getDiscussionHash();
    }

    @ModelAttribute("sender")
    String sender(Principal user) {
        return user.getName();
    }

    @ModelAttribute("receiverName")
    String receiverName(@ModelAttribute("receiver") String receiver) {
        return appUserService.findByEmail(receiver).getFullName();
    }

    @GetMapping("/messages/{sender}/{receiver}")
    @ResponseBody
    public List<ChatMessage> messages(@PathVariable("receiver") String sender, @PathVariable("sender") String receiver) {
        return chatMessageService.findBySenderAndReceiver(sender, receiver);
    }
}
