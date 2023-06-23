package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.discussions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDiscussionRepository extends CrudRepository<DiscussionEntity, Long> {
    boolean existsByDiscussionHash(String hash);

    DiscussionEntity findByDiscussionHash(String discussionhash);
}
