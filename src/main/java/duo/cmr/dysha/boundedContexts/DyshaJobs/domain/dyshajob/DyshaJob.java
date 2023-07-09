package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DyshaJob {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime postedDate;
    private String employeur;
    private String location;
    private Long userId;
    private List<String> images;

    public DyshaJob(String title, String description, String employeur, String location, Long userId, List<String> images) {
        this.title = title;
        this.description = description;
        this.employeur = employeur;
        this.location = location;
        this.postedDate = LocalDateTime.now();
        this.userId = userId;
        this.images = images;
    }

    public DyshaJob(){}

    public  String tableName(){
        return   "Job_photo_image";
    }

    public boolean hasNoImage(){
        return images.isEmpty();
    }

    @Override
    public String toString() {
        return "DyshaJob{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", postedDate=" + postedDate +
                ", employeur='" + employeur + '\'' +
                ", location='" + location + '\'' +
                ", userId=" + userId +
                ", images='" + images.toString() + '\'' +
                '}';
    }
}
