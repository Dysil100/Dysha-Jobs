package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaJobRepository;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaJobRepositoryImpl implements DyshaJobRepository {
    DaoDyshaJobRepository dyshaJobRepository;
    DyshaFileRepository dyshaFileRepository;
    @Override
    public List<DyshaJob> findAllById(List<Long> jobIds) {
        return toDyshaJobList(dyshaJobRepository.findAllById(jobIds));
    }

    @Override
    public void save(DyshaJob dyshaJob) {
        dyshaJobRepository.save(toDyshaJobEntity(dyshaJob));
    }

    @Override
    public DyshaJob findByID(Long id) {
        return toDyshaJob(dyshaJobRepository.findById(id).get());
    }

    private DyshaJobEntity toDyshaJobEntity(DyshaJob dj) {
        return  new DyshaJobEntity(dj.getTitle(), dj.getDescription(), dj.getPostedDate(), dj.getEmployeur(), dj.getLocation(), dj.getUserId());
    }

    @Override
    public List<DyshaJob> findJobsByExprr(String query) {
        return null;
    }
    @Override
    public List<DyshaJob> restLostJobs(String query) {
        return null;
    }

    @Override
    public List<DyshaJob> findAll() {
        return toDyshaJobList(dyshaJobRepository.findAll());
    }

    private List<DyshaJob> toDyshaJobList(Iterable<DyshaJobEntity> all) {
        List<DyshaJob> result = new ArrayList<>();
        all.forEach(e -> result.add(toDyshaJob(e)));
        return result;
    }

    private DyshaJob toDyshaJob(DyshaJobEntity e) {
        return new DyshaJob(e.getId(), e.getTitle(), e.getDescription(), e.getPostedDate(), e.getEmployeur(), e.getLocation(), e.getUserId(), dyshaFileRepository.findLastByTableNameAndEntityIdAndFileType("dyshajob", e.getId(), "image") );
    }

}
