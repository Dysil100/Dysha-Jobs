package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaJobRepository;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import duo.cmr.dysha.boundedContexts.generalhelpers.generalresearch.MyGeneralSearcher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static duo.cmr.dysha.boundedContexts.generalhelpers.DateTimeHelper.dateToString;
import static duo.cmr.dysha.boundedContexts.generalhelpers.DateTimeHelper.stringToDate;

@AllArgsConstructor
@Repository
public class DyshaJobRepositoryImpl implements DyshaJobRepository {
    DaoDyshaJobRepository daoDyshaJobRepository;
    DyshaFilesService dyshaFilesService;
    MyGeneralSearcher<DyshaJob> generalSearcher ;
    @Override
    public List<DyshaJob> findAllById(List<Long> jobIds) {
        return toDyshaJobList(daoDyshaJobRepository.findAllById(jobIds));
    }

    @Override
    public void save(DyshaJob dyshaJob) {
        daoDyshaJobRepository.save(toDyshaJobEntity(dyshaJob));
    }

    @Override
    public DyshaJob findByID(Long id) {
        return toDyshaJob(daoDyshaJobRepository.findById(id).get());
    }

    @Override
    public void update(DyshaJob dyshaJob) {
        DyshaJobEntity jobEntity = toDyshaJobEntity(dyshaJob);
        List<String> images = jobEntity.getImages();
        String[] arrayImages = new String[images.size()];

        for (int i = 0; i < images.size(); i++) {
            arrayImages[i] = images.get(i);
        }
        daoDyshaJobRepository.update(jobEntity.getTitle(), jobEntity.getDescription(), jobEntity.getPostedDate(),
                jobEntity.getEmployeur(), jobEntity.getLocation(), jobEntity.getUserId(), arrayImages, dyshaJob.getId());
    }

    private DyshaJobEntity toDyshaJobEntity(DyshaJob dj) {
        return  new DyshaJobEntity(dj.getTitle(), dj.getDescription(), dj.getPostedDate(), dj.getEmployeur(), dj.getLocation(), dj.getUserId(), dj.getImages());
    }

    @Override
    public List<DyshaJob> findJobsByExprr(String query) {
        return generalSearcher.searchThisExprIn(query, findAll());
    }
    @Override
    public List<DyshaJob> restListJobs(String query) {
        return substractResult(findAll(), findJobsByExprr(query));
    }

    private List<DyshaJob> substractResult(List<DyshaJob> all, List<DyshaJob> jobsByExprr) {
        return all.stream().filter(e -> !jobsByExprr.contains(e)).toList();
    }

    @Override
    public List<DyshaJob> findAll() {
        return toDyshaJobList(daoDyshaJobRepository.findAll());
    }

    private List<DyshaJob> toDyshaJobList(Iterable<DyshaJobEntity> all) {
        List<DyshaJob> result = new ArrayList<>();
        all.forEach(e -> result.add(toDyshaJob(e)));
        return result;
    }

    private DyshaJob toDyshaJob(DyshaJobEntity e) {
        return new DyshaJob(e.getId(), e.getTitle(), e.getDescription(), stringToDate(dateToString(e.getPostedDate())), e.getEmployeur(), e.getLocation(), e.getUserId(), e.getImages());
    }

}
