package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.interfaces.repositories;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.chatmessage.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {
    void save(ChatMessage msg);

    List<ChatMessage> findBySenderAndReceiver(String sender, String receiver);

    List<ChatMessage> findByDiscussionHash(String uuuuid);
}
