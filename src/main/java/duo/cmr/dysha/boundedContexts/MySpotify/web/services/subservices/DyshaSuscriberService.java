package duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices;


import duo.cmr.dysha.boundedContexts.MySpotify.domain.subscriber.DyshaSubscriber;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaSubscriberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DyshaSuscriberService {
    private DyshaSubscriberRepository subscriberRepository;

    public void saveAbo(DyshaSubscriber subscriber) {
        subscriberRepository.save(subscriber);
    }
}
