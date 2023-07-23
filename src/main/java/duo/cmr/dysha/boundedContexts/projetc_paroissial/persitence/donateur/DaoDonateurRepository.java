package duo.cmr.dysha.boundedContexts.projetc_paroissial.persitence.donateur;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoDonateurRepository extends CrudRepository<DonateurEntity, Long> {
}
