package duo.cmr.dysha.boundedContexts.MySpotify.persistance.dyshacomment;

import duo.cmr.dysha.boundedContexts.MySpotify.domain.comment.DyshaComment;
import duo.cmr.dysha.boundedContexts.MySpotify.web.services.interfaces.DyshaCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DyshaCommentRepositoryImpl implements DyshaCommentRepository {

    private DaodyshaCommentRepository daodyshaCommentRepository;

    @Override
    public List<DyshaComment> findAllByMusicId(Long musicId) {
        return toDyshCommentList(daodyshaCommentRepository.findAllByMusicId(musicId));
    }

    private List<DyshaComment> toDyshCommentList(Iterable<DyshaCommentEntity> all) {
        List<DyshaComment> result = new ArrayList<>();
        all.forEach(e -> result.add(toDyshComment(e)));
        return  result;
    }

    private DyshaComment toDyshComment(DyshaCommentEntity e) {
        return new DyshaComment(e.getId(), e.getContent(), e.getMusicId(), e.getUserId(), e.getCreatedAt());
    }
}
