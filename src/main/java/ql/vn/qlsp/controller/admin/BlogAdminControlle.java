package ql.vn.qlsp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ql.vn.qlsp.entity.BlogEntity;
import ql.vn.qlsp.entity.BlogRenderEntity;
import ql.vn.qlsp.service.BlogService;

import java.sql.SQLException;

@RestController
@RequestMapping("admin/blog")
public class BlogAdminControlle {

    @Autowired
    BlogService blogService;

    @GetMapping("get-all-blogs-ByNameContaing")
    public Page<BlogEntity> getAllBlogByName(@RequestParam String title , Pageable pageable){
        return blogService.getBlogsByTitleContaining(title,pageable);
    }

    @PostMapping("post-update-imgBLog")
    public ResponseEntity<?> updateImgBlog(@RequestParam("idBlog") Integer idBlog , @RequestParam(value="fileMainImg" , required = false) MultipartFile fileMainImg , @RequestParam(value="fileBackgroungImg", required = false) MultipartFile fileBackgroungImg){
        System.out.println("update img "+ idBlog);
        try{
            BlogEntity blogEntity = blogService.updateImgBlog(idBlog,fileMainImg,fileBackgroungImg);
            return ResponseEntity.ok(blogEntity);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("post-addBlog")
    public ResponseEntity<?> addBlog(@RequestBody BlogEntity blogEntity){
        try{
            BlogEntity blogEntity1 = blogService.addBlog(blogEntity);
            return ResponseEntity.ok(blogEntity1);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("post-updateBlog")
    public ResponseEntity<?> updateBlog(@RequestBody BlogEntity blogEntity){
        try{
            BlogEntity blogEntity1 = blogService.updateBlog(blogEntity);
            return ResponseEntity.ok(blogEntity1);
        }catch (java.sql.SQLException sqlEx){
            // Xử lý lỗi SQL ở đây
            return new ResponseEntity<>(sqlEx.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("post-delete-blog")
    public ResponseEntity<?> deleteBlog(@RequestParam Integer idBlog){
        try{
            blogService.deleteBlog(idBlog);
            return ResponseEntity.ok("ok");
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("post-add-blog-render")
    public ResponseEntity<?> deleteBlog(@RequestBody BlogRenderEntity blogRenderEntity){
        try{
            BlogRenderEntity blogRenderEntity1 = blogService.addorUpdateBlogRender(blogRenderEntity);
            return ResponseEntity.ok(blogRenderEntity1);
        }catch ( Exception ex){
            return new ResponseEntity<>("cập nhật blog render không thành công " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
