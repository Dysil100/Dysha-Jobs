package duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.subscriber.DyshaSubscriber;

public interface DyshaSubscriberRepository {
    void save(DyshaSubscriber subscriber);
}
