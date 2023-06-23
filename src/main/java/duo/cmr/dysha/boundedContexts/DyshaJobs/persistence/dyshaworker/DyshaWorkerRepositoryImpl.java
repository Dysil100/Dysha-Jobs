package duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshaworker;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob.DyshaJobRepositoryImpl;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.workerjobrelation.WorkerJobRelationRepositoryImpl;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.interfaces.DyshaWorkerRepository;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaWorkerRepositoryImpl implements DyshaWorkerRepository {

    DaoDyshaWorkerRepository daoDyshaWorkerRepository;
    WorkerJobRelationRepositoryImpl workerJobRelationRepository;
    DyshaJobRepositoryImpl dyshaJobRepository;
    DyshaFilesService dyshaFilesService;

    @Override
    public List<DyshaWorker> findAllWorker() {
        return toWorkerList(daoDyshaWorkerRepository.findAll());
    }

    @Override
    public void save(DyshaWorker worker) {
        daoDyshaWorkerRepository.save(toDyshaWorkerEntity(worker));
    }

    @Override
    public DyshaWorker findById(Long id) {
        return !daoDyshaWorkerRepository.existsById(id) ? null : toWorker(daoDyshaWorkerRepository.findById(id).get());
    }

    @Override
    public DyshaWorker findByUserId(Long userId) {
        return daoDyshaWorkerRepository.existsByUserId(userId) ? toWorker(daoDyshaWorkerRepository.findByUserId(userId)) : null;
    }

    @Override
    public boolean existByuserId(Long userId) {
        return daoDyshaWorkerRepository.existsByUserId(userId);
    }

    private DyshaWorkerEntity toDyshaWorkerEntity(DyshaWorker dw) {
        return new DyshaWorkerEntity(dw.getName(), dw.getDescription(), dw.getLocalisation(), dw.getStartedOn(), dw.getUserId());
    }

    private List<DyshaWorker> toWorkerList(Iterable<DyshaWorkerEntity> all) {
        List<DyshaWorker> result = new ArrayList<>();
        all.forEach(e -> result.add(toWorker(e)));
        return result;
    }

    private DyshaWorker  toWorker(DyshaWorkerEntity e) {
        List<DyshaJob> allJobsByWorkerId = dyshaJobRepository.findAllById(workerJobRelationRepository.findAllByWorkerId(e.getId()).stream().map(WorkerJobRelation::getJobId).toList());
        String profilImageName = dyshaFilesService.findProfilImageFor(e.getUserId());
        List<WorkerJobRelation> allWorkerJobRelationsByWorkerId = workerJobRelationRepository.findAllByWorkerId(e.getId());
        return new DyshaWorker(e.getId(), e.getName(), allJobsByWorkerId, allWorkerJobRelationsByWorkerId, e.getDescription(), e.getLocation(), e.getStartedOn(), e.getUserId(), profilImageName);
    }
}
