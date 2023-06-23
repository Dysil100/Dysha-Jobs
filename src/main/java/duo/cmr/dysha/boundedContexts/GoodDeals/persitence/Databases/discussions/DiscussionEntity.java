package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.discussions;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("discussion")
public class DiscussionEntity {

    @Id
    private Long id;
    private String discussionHash;
    private LocalDateTime createdAt;


    public DiscussionEntity(String discussionHash, LocalDateTime createdAt) {
        this.discussionHash = discussionHash;
        this.createdAt = createdAt;
    }
}
