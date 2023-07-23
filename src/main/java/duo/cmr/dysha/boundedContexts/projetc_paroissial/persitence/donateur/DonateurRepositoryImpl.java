package duo.cmr.dysha.boundedContexts.projetc_paroissial.persitence.donateur;

import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donateur.Donateur;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.domain.donations.Donation;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.persitence.donation.DonationRepositoryImpl;
import duo.cmr.dysha.boundedContexts.projetc_paroissial.web.services.interfaces.DonateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class DonateurRepositoryImpl implements DonateurRepository {
    private DaoDonateurRepository daoDonateurRepository;
    @Override
    public void save(Donateur donateur) {
        daoDonateurRepository.save(toDonateurEntity(donateur));
    }

    @Override
    public List<Donateur> allDonateurs() {
        return toDonateurList(daoDonateurRepository.findAll());
    }

    @Override
    public Donateur findById(Long idDonateur) {
        return toDonateur(daoDonateurRepository.findById(idDonateur).get());
    }

    private DonateurEntity toDonateurEntity(Donateur donateur) {
        return new DonateurEntity(donateur.getNom(), donateur.getPrenom(), donateur.getSexe(), donateur.getAdresse_telephonique(),
                donateur.isCroyant(), donateur.isVisiteur(), donateur.getInstitution(), donateur.getPhoto());
    }

    private Donateur toDonateur(DonateurEntity e) {
        return new Donateur(e.getId_donneur(), e.getNom(), e.getPrenom(), e.getSexe(), e.getAdresse_telephonique(),
                e.isCroyant(), e.isVisiteur(), e.getInstitution(), e.getPhoto(), List.of());
    }

    private List<Donateur> toDonateurList(Iterable<DonateurEntity> all) {
        List<Donateur> donateurs = new ArrayList<>();
        all.forEach(e -> donateurs.add(toDonateur(e)));
        return donateurs;
    }
}
