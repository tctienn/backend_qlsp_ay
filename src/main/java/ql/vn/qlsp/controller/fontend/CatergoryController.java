package ql.vn.qlsp.controller.fontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ql.vn.qlsp.entity.CatergoryEntity;
import ql.vn.qlsp.repository.CatergoryRepository;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/catergory")
public class CatergoryController {

    @Autowired
    private CatergoryRepository catergoryRepository;

    @GetMapping("get-catergorys")
    public List<CatergoryEntity> getCatergorys(){
        return catergoryRepository.findAll();
    }
}
