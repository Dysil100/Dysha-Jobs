package duo.cmr.dysha.boundedContexts.MySpotify.persistance.likes;

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
@Table("likes")
public class MusicLikesEntity {

    @Id
    private Long id;
    private Long musicId;
    private Long userId;
    private String type;
    private LocalDateTime createdAt;

    public MusicLikesEntity(Long musicId, Long userId, String type, LocalDateTime createdAt) {
        this.musicId = musicId;
        this.userId = userId;
        this.type = type;
        this.createdAt = createdAt;
    }
}
