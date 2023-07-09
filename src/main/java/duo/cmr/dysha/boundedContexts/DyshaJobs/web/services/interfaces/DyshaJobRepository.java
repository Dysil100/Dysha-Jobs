package duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;

import java.util.List;

public interface DyshaJobRepository {
    List<DyshaJob> findJobsByExprr(String query);

    List<DyshaJob> findAll();

    List<DyshaJob> restListJobs(String query);

    List<DyshaJob> findAllById(List<Long> jobIds);

    void save(DyshaJob dyshaJob);

    DyshaJob findByID(Long id);

    void update(DyshaJob dyshaJob);
}
