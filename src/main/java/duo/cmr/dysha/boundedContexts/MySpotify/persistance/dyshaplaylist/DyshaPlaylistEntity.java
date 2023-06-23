package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshaplaylist;

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
@Table("playlist")
public class DyshaPlaylistEntity {

    @Id
    private Long id;
    private String name;
    private Long userId;
    private LocalDateTime createdAt;

    public DyshaPlaylistEntity(String name, Long userId, LocalDateTime createdAt) {
        this.name = name;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
