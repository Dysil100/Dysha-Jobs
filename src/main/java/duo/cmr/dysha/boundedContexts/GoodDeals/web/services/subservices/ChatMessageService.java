package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.chatmessage.ChatMessage;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.interfaces.repositories.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ChatMessageService {
    ChatMessageRepository chatMessageRepository;

    public void save(ChatMessage msg) {
        chatMessageRepository.save(msg);
    }

    public List<ChatMessage> findBySenderAndReceiver(String sender, String receiver) {
        return chatMessageRepository.findBySenderAndReceiver(sender, receiver);
    }
}
