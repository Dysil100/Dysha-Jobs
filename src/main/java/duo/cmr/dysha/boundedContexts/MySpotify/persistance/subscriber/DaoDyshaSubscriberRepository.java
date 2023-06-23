package duo.cmr.dysha.boundedContexts.MySpotify.persistance.subscriber;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDyshaSubscriberRepository extends CrudRepository<DyshaSubscriberEntity, Long> {
}
