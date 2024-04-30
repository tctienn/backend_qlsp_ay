package ql.vn.qlsp.controller.fontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ql.vn.qlsp.entity.BrandsEntity;
import ql.vn.qlsp.repository.BrandsRepository;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/brand")
public class BrandController {

    @Autowired
    private BrandsRepository brandsRepository;

    @GetMapping("get-brands")
    public List<BrandsEntity> getBrands(){
        return brandsRepository.findAll();
    }

}
