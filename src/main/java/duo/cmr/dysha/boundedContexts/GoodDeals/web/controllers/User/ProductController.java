package duo.cmr.dysha.boundedContexts.GoodDeals.web.controllers.User;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.product.Product;
import duo.cmr.dysha.boundedContexts.GoodDeals.domain.oders.FilterForm;
import duo.cmr.dysha.boundedContexts.GoodDeals.domain.oders.InputSearchForm;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices.ProductService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.dysha.boundedContexts.dyshafile.domain.FileInfos;
import duo.cmr.dysha.boundedContexts.dyshafile.web.services.DyshaFilesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static duo.cmr.dysha.boundedContexts.routen.Routen.*;

@AllArgsConstructor
@Controller
public class ProductController {

    private DyshaFilesService filesService;
    private ProductService productService;
    private ServiceSupreme serviceSupreme;

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "Salut  " + userByEmail.getFirstName() + " trouvons ensemble le Produit dont tu as besoin.";
    }

    @GetMapping("/goodeals/profil")
    public String userprofil(Model model, @ModelAttribute("searchform") InputSearchForm searchForm,  @ModelAttribute("text") String text, @ModelAttribute("user") AppUser currentUser) {
        List<Product> allByUserId = productService.findAllByUserId(currentUser.getId());
        System.out.println("Mes produits = " + allByUserId);
        model.addAttribute("myProducts", allByUserId);
        model.addAttribute("profile", currentUser);
        model.addAttribute("searchform", searchForm);
        return "good-deals/gooddealsprofil";
    }

    @GetMapping(PRODUCTLISTE)
    public String acceuil(Model model, @ModelAttribute("searchform") InputSearchForm searchForm){
       // searchSetUp(model, null);
        model.addAttribute("searchform", searchForm);
        model.addAttribute("products", productService.findAll());
        return "good-deals/productliste";
    }

    // TODO: 29.04.2023 repair this hier and in the frontend
    @GetMapping(PRODUCTSEARCH)
    public String productSearch(Model model, @ModelAttribute("searchform") InputSearchForm searchForm,
                                @ModelAttribute("filterForm") FilterForm filterForm){
        //setup search:
        model.addAttribute("filterForm", filterForm);
        model.addAttribute("categories", productService.getCathegories());
        model.addAttribute("regions", productService.getRegions());
        model.addAttribute("searchform", searchForm);
        model.addAttribute("searchedResult", productService.seachByExpr(searchForm.getQuery(), filterForm));

        return "good-deals/productsearchliste";
    }

    @GetMapping(NEWPRODUCT)
    public String product(Model model, @ModelAttribute("productForm") Product form) {
        model.addAttribute("regions", productService.getRegionCitiesMap());
        model.addAttribute("cathegories", productService.getCathegories());
        model.addAttribute("productForm", form);

        return "good-deals/newproduct";
    }

    @PostMapping(NEWPRODUCT)
    public String productPost(Model model, @ModelAttribute("productForm") Product form, @ModelAttribute("user") AppUser creator,
                              List<MultipartFile> productImages) {
        form.setCreatedAt(LocalDateTime.now());
        form.setUserId(creator.getId());
        form.setUserEmail(creator.getEmail());
        List<String> productImagesFileNames = filesService.saveAll(new FileInfos(creator.getId(), creator.getId(), "products", "images"), productImages);
        form.setImages(productImagesFileNames);
        productService.save(form);
        model.addAttribute("products", productService.findAll());
        return "redirect:" + PRODUCTLISTE;
    }

    @GetMapping("/product/details/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "good-deals/productdetails";
    }

    // Méthode qui renvoie la liste des villes en fonction de la région
    @GetMapping("/villes")
    @ResponseBody
    public List<String> getCities(@RequestParam("region") String region) {
        return  productService.getCitiesOf(region);
    }

    @ModelAttribute("productForm")
    Product productForm() {
        return new Product(null,null, null, null, 0.0, false, false
                , null, null, null, null, null, null, null);
    }

    @ModelAttribute("InputSearchForm")
    InputSearchForm inputSearchForm() {
        return new InputSearchForm(null);
    }

    @ModelAttribute("user")
    AppUser profile(Principal user) {
        String name = user.getName();
        return serviceSupreme.getUserByEmail(name);
    }

}
