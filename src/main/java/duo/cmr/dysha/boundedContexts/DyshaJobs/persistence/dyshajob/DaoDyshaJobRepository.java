package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface DaoDyshaJobRepository extends CrudRepository<DyshaJobEntity, Long> {
    @Transactional
    @Modifying
    @Query("""
     UPDATE dyshajob SET title = :title, description = :description, posted_date = :postedDate, employeur = :employeur,
     location = :location, user_id = :userId, images = :images WHERE id = :jobId
 """)
    void update(@Param("title")String title, @Param("description")String description, @Param("postedDate")LocalDateTime postedDate,
                @Param("employeur")String employeur, @Param("location")String location, @Param("userId")Long userId, @Param("images")String [] images, @Param("jobId")Long jobId);
}