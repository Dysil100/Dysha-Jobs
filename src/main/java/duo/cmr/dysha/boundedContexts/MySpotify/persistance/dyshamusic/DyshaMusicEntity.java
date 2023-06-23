package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshamusic;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("dyshamusic")
public class DyshaMusicEntity {

    @Id
    private Long id;
    private String title;
    private String description;
    private String musicFileName;
    private String thumbnailFileName;
    private LocalDateTime postedOn;
    private Long userId;

    public DyshaMusicEntity(String title, String description, String musicFileName, String thumbnailFileName, LocalDateTime postedOn, Long userId) {
        this.title = title;
        this.description = description;
        this.musicFileName = musicFileName;
        this.thumbnailFileName = thumbnailFileName;
        this.postedOn = postedOn;
        this.userId = userId;
    }
}
