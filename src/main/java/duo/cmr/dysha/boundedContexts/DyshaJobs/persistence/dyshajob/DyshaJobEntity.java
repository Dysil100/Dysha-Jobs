package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("dyshajob")
public class DyshaJobEntity {

    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDateTime postedDate;
    private String employeur;
    private String location;
    private Long userId;
    private List<String> images;

    public DyshaJobEntity(String title, String description, LocalDateTime postedDate, String employeur, String location, Long userId, List<String> images) {
        this.title = title;
        this.description = description;
        this.postedDate = postedDate;
        this.employeur = employeur;
        this.location = location;
        this.userId = userId;
        this.images = images;
    }
}
