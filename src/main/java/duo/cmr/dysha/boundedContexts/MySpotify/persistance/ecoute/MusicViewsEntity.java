package duo.cmr.dysha.boundedContexts.MySpotify.persistance.ecoute;

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
@Table("ecoute")
public class MusicViewsEntity {
    @Id
    private Long id;
    private Long musicId;
    private Long userId;
    private String type;
    private LocalDateTime viewedAt;

    public MusicViewsEntity(Long musicId, Long userId, String type, LocalDateTime viewedAt) {
        this.musicId = musicId;
        this.userId = userId;
        this.type = type;
        this.viewedAt = viewedAt;
    }
}
