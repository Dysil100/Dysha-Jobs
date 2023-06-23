package duo.cmr.dysha.boundedContexts.MySpotify.domain.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class DyshaComment {

        private Long id;
        private String content;
        private Long musicId;
        private Long userId;
        private LocalDateTime createdAt;

    public DyshaComment(String content, Long musicId, Long userId, LocalDateTime createdAt) {
        this.content = content;
        this.musicId = musicId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public DyshaComment(String content, Long musicId, Long userId) {
        this.content = content;
        this.musicId = musicId;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

}
