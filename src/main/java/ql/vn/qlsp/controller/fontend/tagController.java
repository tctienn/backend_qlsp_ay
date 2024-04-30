package ql.vn.qlsp.controller.fontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ql.vn.qlsp.entity.TagEntity;
import ql.vn.qlsp.repository.TagRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/tag")
public class tagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("get-tags")
    public List<TagEntity> getTags(){

        return tagRepository.findAll();
    }

}
