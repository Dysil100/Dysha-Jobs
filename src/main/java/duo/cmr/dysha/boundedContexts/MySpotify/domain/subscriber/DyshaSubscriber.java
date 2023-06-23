package duo.cmr.dysha.boundedContexts.MySpotify.domain.subscriber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DyshaSubscriber {
    private Long id;
    private Long subscriberId;
    private Long channelId;

    public DyshaSubscriber(Long subscriberId, Long channelId) {
        this.subscriberId = subscriberId;
        this.channelId = channelId;
    }
}
