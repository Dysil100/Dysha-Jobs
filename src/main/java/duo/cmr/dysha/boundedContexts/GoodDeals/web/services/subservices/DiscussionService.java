package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.discussion.Discussion;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.interfaces.repositories.DiscussionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DiscussionService {

   DiscussionRepository discussionRepository;

    public List<Discussion> loadDiscussionsFor(String sender) {
        return discussionRepository.findAllFor(sender);
    }

    public boolean existByDiscussionHash(String sb) {
        return discussionRepository.existByDiscussionHash(sb);
    }

    public void save(Discussion discussion) {
        discussionRepository.save(discussion);
    }

    public Discussion finByDiscussionHash(String discussionhash) {
        return discussionRepository.findByDiscussionHash(discussionhash);
    }
}
