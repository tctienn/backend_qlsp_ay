package ql.vn.qlsp.controller.fontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ql.vn.qlsp.entity.SizeEntity;
import ql.vn.qlsp.repository.SizeRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/size")
public class SizeController {

    @Autowired
    private SizeRepository sizeRepository;

    @GetMapping("get-sizes")
    public List<SizeEntity> getSizes(){

        return sizeRepository.findAll();
    }



}
