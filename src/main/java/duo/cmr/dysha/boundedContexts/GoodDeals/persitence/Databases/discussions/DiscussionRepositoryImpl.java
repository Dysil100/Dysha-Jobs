package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.discussions;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.discussion.Discussion;
import duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.chatmessage.ChatMessageRepositoryImpl;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.security.ChatDiscussionHash;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.interfaces.repositories.DiscussionRepository;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.database.appuser.AppUserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class DiscussionRepositoryImpl implements DiscussionRepository {

    DaoDiscussionRepository daoDiscussionRepository;
    ChatMessageRepositoryImpl chatMessageRepository;
    AppUserRepositoryImpl appUserRepositoryImpl;
    private ChatDiscussionHash chimpl;
    @Override
    public List<Discussion> findAllFor(String sender) {
        return toDiscussionList(daoDiscussionRepository.findAll()).stream().filter(d -> d.getUsers().size()>1).filter(d ->d.getUsers().stream().map(AppUser::getEmail).toList().contains(sender)).collect(Collectors.toList());
    }

    @Override
    public boolean existByDiscussionHash(String sb) {
        return daoDiscussionRepository.existsByDiscussionHash(sb);
    }

    @Override
    public void save(Discussion discussion) {
        daoDiscussionRepository.save(toDiscussionEntity(discussion));
    }

    @Override
    public Discussion findByDiscussionHash(String discussionHash) {
        if (daoDiscussionRepository.existsByDiscussionHash(discussionHash)) {
            return toDiscussion(daoDiscussionRepository.findByDiscussionHash(discussionHash));
        } else{
            save(new Discussion(discussionHash));
        }
        return toDiscussion(daoDiscussionRepository.findByDiscussionHash(discussionHash));
    }

    private List<Discussion> toDiscussionList(Iterable<DiscussionEntity> all) {
        List<Discussion> result = new ArrayList<>();
        all.forEach(d-> result.add(toDiscussion(d)) );
        return result;
    }

    private DiscussionEntity toDiscussionEntity(Discussion d) {
        return new DiscussionEntity(d.getDiscussionHash(), d.getCreatedAt());
    }

    private Discussion toDiscussion(DiscussionEntity e){
        List<AppUser> users = appUserRepositoryImpl.findByIds(chimpl.getUserIdsFromChatDiscussionHash(e.getDiscussionHash()));
        return new Discussion(e.getId(), e.getDiscussionHash(), chatMessageRepository.findByDiscussionHash(e.getDiscussionHash()), e.getCreatedAt(), users);
    }
}
