package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshacomment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table("comment")
public class DyshaCommentEntity {

    @Id
    private Long id;
    private String content;
    private Long musicId;
    private Long userId;
    private LocalDateTime createdAt;

    public DyshaCommentEntity(String content, Long musicId, Long userId, LocalDateTime createdAt) {
        this.content = content;
        this.musicId = musicId;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
