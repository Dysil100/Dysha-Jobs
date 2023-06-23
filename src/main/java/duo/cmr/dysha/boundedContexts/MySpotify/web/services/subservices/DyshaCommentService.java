package duo.cmr.dysha.boundedContexts.MySpotify.web.services.subservices;

import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DyshaCommentService {
    private DyshaCommentRepository commentRepository;
}
