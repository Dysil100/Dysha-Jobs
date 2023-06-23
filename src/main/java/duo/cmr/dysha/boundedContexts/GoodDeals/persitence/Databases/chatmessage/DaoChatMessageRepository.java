package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.chatmessage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoChatMessageRepository extends CrudRepository<ChatMessageEntity, Long> {

    Iterable<ChatMessageEntity> findByDiscussionHash(String discussionHash);
    Iterable<ChatMessageEntity> findAllBySenderAndReceiver(String sender, String receiver);
    Iterable<ChatMessageEntity> findAllByReceiverAndSender(String sender, String receiver);
}
