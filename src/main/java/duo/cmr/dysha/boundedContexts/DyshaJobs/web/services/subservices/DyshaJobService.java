package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaJobRepository;
import duo.cmr.dysha.boundedContexts.generalhelpers.matchers.MyMatchValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DyshaJobService {

    private  MyMatchValidator<DyshaJob> matchValidator;
    private  DyshaJobRepository dyshaJobRepository;

    public List<DyshaJob> findAllJobsByExorr(String query)  {
        return dyshaJobRepository.findJobsByExprr(query);
    }

    public List<DyshaJob> restListJobs(String query) {
        return dyshaJobRepository.restListJobs(query);
    }

    public List<DyshaJob> findAll() {
        return dyshaJobRepository.findAll();
    }

    public void save(DyshaJob dyshaJob) {
         dyshaJobRepository.save(dyshaJob);
    }

    public DyshaJob getJobById(Long id) {
        return dyshaJobRepository.findByID(id);
    }

    public boolean validates(DyshaJob dyshaJob) {
        return matchValidator.matches(dyshaJob);
    }

    public void update(DyshaJob dyshaJob) {
        dyshaJobRepository.update(dyshaJob);
    }
}
