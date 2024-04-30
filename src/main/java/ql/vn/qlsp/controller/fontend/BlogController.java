package ql.vn.qlsp.controller.fontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ql.vn.qlsp.entity.BlogEntity;
import ql.vn.qlsp.entity.BlogRenderEntity;
import ql.vn.qlsp.repository.BlogRenderRepository;
import ql.vn.qlsp.repository.BlogRepository;
import ql.vn.qlsp.service.BlogService;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/blog")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    BlogRenderRepository blogRenderRepository;
    @Autowired
    BlogRepository blogRepository;

    @GetMapping("get-blogs")
    public Page<BlogEntity> getBlogs(Pageable pageable){
        return blogService.getBlogs(pageable);
    }
    @GetMapping("get-blog-by-render")
    public Page<BlogRenderEntity> getBlogsByRender(Pageable pageable){

        return blogService.getBlogRenders(pageable);
    }

    @GetMapping("get-blog-by-render-type")
    public Page<BlogRenderEntity> getBlogRenderByType(Pageable pageable , @RequestParam(name="type") int type){

        return blogRenderRepository.findAllByType(type, pageable);
    }
    @GetMapping("get-blog-by-id/{id}")
    public BlogEntity getBlogById(@PathVariable(name = "id") int id){

        return blogRepository.getAllById(id);
    }
}
