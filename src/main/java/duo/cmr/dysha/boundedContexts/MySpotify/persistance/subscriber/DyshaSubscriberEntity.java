package duo.cmr.dysha.boundedContexts.MySpotify.persistance.subscriber;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table("subscription")
public class DyshaSubscriberEntity {
    private Long id;
    private Long subscriberId;
    private Long channelId;

    public DyshaSubscriberEntity(Long subscriberId, Long channelId) {
        this.subscriberId = subscriberId;
        this.channelId = channelId;
    }
}
