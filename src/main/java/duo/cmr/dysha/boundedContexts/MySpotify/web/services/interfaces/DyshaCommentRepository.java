package duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.comment.DyshaComment;

import java.util.List;

public interface DyshaCommentRepository {
    List<DyshaComment> findAllByMusicId(Long id);
}
