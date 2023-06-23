package duo.cmr.dysha.boundedContexts.GoodDeals.persitence.Databases.produit;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoProductRepository extends CrudRepository<ProductEntity, Long> {

    @Transactional
    @Query(value = """
    SELECT *
    FROM product
    WHERE
        title ILIKE '%' || :searchTerm || '%' OR
        description ILIKE '%' || :searchTerm || '%' OR
        cathegorie ILIKE '%' || :searchTerm || '%' OR
        region ILIKE '%' || :searchTerm || '%' OR
        ville ILIKE '%' || :searchTerm || '%' OR
        quartier ILIKE '%' || :searchTerm || '%'
        OR
        :searchTerm ILIKE '%' || title || '%' OR
        :searchTerm ILIKE '%' || description || '%' OR
        :searchTerm ILIKE '%' || cathegorie || '%' OR
        :searchTerm ILIKE '%' || region || '%' OR
        :searchTerm ILIKE '%' || ville || '%' OR
        :searchTerm ILIKE '%' || quartier || '%';
    """)
    Iterable<ProductEntity> searchAllByQuery(@Param("searchTerm") String query);
    Iterable<ProductEntity> findAllByUserId(Long userId);
}
