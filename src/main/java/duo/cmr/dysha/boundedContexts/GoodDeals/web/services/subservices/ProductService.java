package duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.Product;
import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.SearcedListResult;
import duo.cmr.dysha.boundedContexts.GoodDeals.domain.oders.FilterForm;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.interfaces.repositories.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@org.springframework.stereotype.Service
public class ProductService {
    private SearchProductService searchProductService;
    private CityService cityService ;
    private ProductRepository productRepository;


    public void save(Product form) {
        productRepository.save(form);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public SearcedListResult seachByExpr(String search, FilterForm filterForm) {
        List<Product> searchedResult = productRepository.searchAllByQuery(search);
        List<Product> restProduct = findAll();
        restProduct.removeAll(searchedResult);
        return new SearcedListResult(searchProductService.filterProducts(filterForm, searchedResult), searchProductService.filterProducts(filterForm, restProduct));
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public List<String> getCathegories() {
        return List.of("Automobiles" , "Immobilier", "Services", "Famille & bébés", "Passe-Temps", "Jardin", "Jardins de maisons", "Animaux de Companie", "Jobs", "Jobs pour Etudiants", "Mode & beauté", "Electroniques", "Musiques, Filmes & Bücher", "Tickets & Carte d'entrée", "Enseignements & Cours", "Cadeaux & echanges", "Voisinage");
    }

    public List<String> getRegions() {
        return  cityService.getRegions();
    }

    public List<String> getCitiesOf(String region) {
        return cityService.getCitiesOf(region);
    }

    public Map<String, List<String>> getRegionCitiesMap() {
        return cityService.getRegionCitiesMap();
    }

    public List<Product> findAllByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }
}
