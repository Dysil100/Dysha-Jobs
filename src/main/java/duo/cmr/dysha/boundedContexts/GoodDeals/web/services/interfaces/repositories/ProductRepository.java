package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.interfaces.repositories;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.Product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> findAllByUserId(Long userId);

    List<Product> searchAllByQuery(String search);
}
