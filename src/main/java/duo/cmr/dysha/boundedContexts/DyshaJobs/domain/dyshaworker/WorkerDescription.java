package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WorkerDescription extends DyshaWorker {
    private String description;
    private String localisation;
}
