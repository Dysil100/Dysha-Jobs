package duo.cmr.dysha.boundedContexts.MySpotify.persistance.subscriber;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.subscriber.DyshaSubscriber;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.subscriber.DaoDyshaSubscriberRepository;
import duo.cmr.dysha.boundedContexts.MySpotify.persistance.subscriber.DyshaSubscriberEntity;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaSubscriberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
@AllArgsConstructor
@Repository
public class DyshaSubscriberRepositoryImpl implements DyshaSubscriberRepository {
   private DaoDyshaSubscriberRepository daoDyshaSubscriberRepository;
    @Override
    public void save(DyshaSubscriber subscriber) {
        daoDyshaSubscriberRepository.save(toSubscriberEntity(subscriber));
    }

    private DyshaSubscriberEntity toSubscriberEntity(DyshaSubscriber subscriber) {
        return new DyshaSubscriberEntity(subscriber.getSubscriberId(), subscriber.getChannelId());
    }
}
