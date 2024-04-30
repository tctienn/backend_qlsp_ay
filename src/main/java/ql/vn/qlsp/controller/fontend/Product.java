package ql.vn.qlsp.controller.fontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.entity.product_catergory.ProductCatergoryEntity;
import ql.vn.qlsp.entity.product_size.ProductSizeEntity;
import ql.vn.qlsp.repository.BrandsRepository;
import ql.vn.qlsp.repository.ProductCatergoryRepository;
import ql.vn.qlsp.repository.ProductRepository;
import ql.vn.qlsp.repository.ProductSizeRepository;
import ql.vn.qlsp.service.GmailService;
import ql.vn.qlsp.service.ProductService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/product")
public class Product {

    @Autowired
     private ProductRepository productRepository ;
    @Autowired
    private BrandsRepository brandsRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ProductCatergoryRepository productCatergoryRepository;
    @Autowired
    private GmailService gmailService;

    @GetMapping("get-product")
    public Page<ProductEntity> getProducts(Pageable pageable){
//        productRepository.deleteById(21);
//        return productRepository.findAll();
//        gmailService.sendEmail("mainichichimvm@gmail.com","ay","<div>ay</div><br/>s");
            return productService.getProductByPageNumber(pageable);
    }

    @GetMapping("/filter")
    public Page<ProductEntity> getproductByBrand(@RequestParam(name = "idbrand") int id , Pageable pageable){
//        System.out.printf("ay" + id);
        return productService.getProductPageByBrandId(id, pageable);
    }
    @GetMapping("/tag/filter")
    public Page<ProductEntity> getproductByTag(@RequestParam(name = "idtag") int id , Pageable pageable){
//        System.out.printf("ay" + id);
        return productService.getProductPageBytagId(id, pageable);
    }

    @GetMapping("detail/{id}")
    public ProductEntity getProductById(@PathVariable(name = "id") Integer id){

        return productRepository.findById(id).orElse(null);
    }
    @GetMapping("filterByName")
    public Page<ProductEntity> getproductByname(@RequestParam(name = "name") String name , Pageable pageable){
//        System.out.printf("ay" + id);
        return productService.getProductByName(name, pageable);
    }

    @GetMapping("get-product-by-idsize")
    public Page<ProductSizeEntity> getProductByIdSize(@RequestParam(name = "id") int id, Pageable pageable){
        return productSizeRepository.findAllBySizeId(id, pageable);
    }

    @GetMapping("get-product-by-idcategory")
    public Page<ProductCatergoryEntity> getproductByIdSize(@RequestParam(name = "id") int id, Pageable pageable ){
        return productCatergoryRepository.findAllByCatergoryId(id,pageable);
    }

}
