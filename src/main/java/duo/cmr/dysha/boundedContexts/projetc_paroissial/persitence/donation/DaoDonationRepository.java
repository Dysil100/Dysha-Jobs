package duo.cmr.dysha.boundedContexts.projetc_paroissial.persitence.donation;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Transactional(readOnly = true)
public interface DaoDonationRepository extends CrudRepository<DonationEntity, Long> {
    Iterable<DonationEntity> findAllByIdDonneur(Long idDonneur);

    @Transactional
    @Modifying
    @Query("UPDATE donations  SET valide = true, utilisateur_validation = :contactName, date_validation = :now WHERE id_donation = :idDonation;")
    void validateNowByIdAndName(@Param("now") LocalDateTime now, @Param("idDonation") Long idDonation,@Param("contactName") String contactName);

    @Transactional
    @Query("SELECT * FROM donations WHERE date_donation >= :start AND date_donation <= :end")
    Iterable<DonationEntity> findAllForCurrentMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
