package duo.cmr.dysha.boundedContexts.avis.persistence;

import duo.cmr.dysha.boundedContexts.avis.forms.Avis;
import duo.cmr.dysha.boundedContexts.avis.web.repositories.AvisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static duo.cmr.dysha.boundedContexts.generalhelpers.DateTimeHelper.dateToString;
import static duo.cmr.dysha.boundedContexts.generalhelpers.DateTimeHelper.stringToDate;

@Repository
@AllArgsConstructor
public class AvisRepositoryImpl implements AvisRepository {
    DaoAvisRepository daoAvisRepository;
    @Override
    public void save(Avis avis) {
        daoAvisRepository.save(toEntity(avis));
    }

    @Override
    public void deleteById(Long id) {
        daoAvisRepository.deleteById(id);
    }

    @Override
    public List<Avis> findAll() {
        List<Avis> alle = new ArrayList<>();
        daoAvisRepository.findAll().forEach(a -> alle.add(toAvis(a)));
        return alle.stream().sorted(Comparator.comparing(Avis::getGeneratedAt, Comparator.reverseOrder())).collect(Collectors.toList());
    }

    private Avis toAvis(AvisEntity e) {
        return new Avis(e.getId(), e.getTelephone(), e.getEmail(), e.getComment(), stringToDate(e.getGeneratedAt()));
    }

    private AvisEntity toEntity(Avis a) {
        return new AvisEntity(a.getTelephone(), a.getEmail(), a.getComment(), dateToString(a.getGeneratedAt()));
    }
}
