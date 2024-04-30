package ql.vn.qlsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ql.vn.qlsp.entity.BlogEntity;
import ql.vn.qlsp.entity.BlogRenderEntity;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.repository.BlogRenderRepository;
import ql.vn.qlsp.repository.BlogRepository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRenderRepository blogRenderRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CheckDateNow checkDateNow;
    @Autowired
    private UploadFile uploadFile;

    // lấy toàn bộ blog
    public Page<BlogEntity> getBlogs(Pageable pageable){

        return blogRepository.findAll(pageable);
    }



    public Page<BlogRenderEntity> getBlogRenders(Pageable pageable){

        return blogRenderRepository.findAll(pageable);
    }

    ///// admin

    public Page<BlogEntity> getBlogsByTitleContaining(String title, Pageable pageable){

        return blogRepository.findAllByTitleContaining(title, pageable);


    }
    @Transactional
    public BlogEntity addBlog(BlogEntity blogEntityParam) throws Exception {
        if(blogEntityParam.getId()!=null){
            throw new Exception("dữ liệu truyền vào không được có id ");
        }
        blogEntityParam.setCreateTime(checkDateNow.getDateNow());
        BlogEntity blogEntity =blogRepository.save(blogEntityParam);
       if(blogEntity ==null){
           throw new Exception("thêm blog không thành công ");
       }
        return  blogEntity;
    }
    @Transactional
    public BlogEntity updateBlog(BlogEntity blogEntityParam) throws Exception {

        if(blogEntityParam.getId()==null){
             throw new Exception("dữ liệu truyền vào không có id ");

        }
        BlogEntity blogEntity = blogRepository.findById(blogEntityParam.getId()).orElse(null);
        if(blogEntity == null){
             throw new Exception("không  tìm thấy blog cần cập nhật ");
        }
        blogEntity.setTitle(blogEntityParam.getTitle());
        blogEntity.setConten(blogEntityParam.getConten());
        blogEntity.setNameCreateUser(blogEntityParam.getNameCreateUser());
        blogEntity.setImgMain(blogEntityParam.getImgMain());
        blogEntity.setImgBackGround(blogEntityParam.getImgBackGround());
        blogEntity.setCreateTime(checkDateNow.getDateNow());
        return blogEntity;

    }
    @Transactional
    public BlogEntity updateImgBlog(Integer idBlog , MultipartFile fileMainImg , MultipartFile fileBackgroungImg) throws Exception {
        BlogEntity blogEntity = blogRepository.getAllById(idBlog);
        if(blogEntity==null)
        {
            throw new Exception("không tìm thấy product id dể update img blog");
        }
        try{
            if(fileMainImg != null){
                uploadFile.removeFile(blogEntity.getImgMain());
            }
            if(fileBackgroungImg != null){
                uploadFile.removeFile(blogEntity.getImgBackGround());
            }

        }catch (Exception ex){
            System.out.println("xóa ảnh trên cloud thất bại:" );
        }

        if(fileMainImg != null){
            blogEntity.setImgMain(uploadFile.uploadFile(fileMainImg));
        }
        if(fileBackgroungImg != null){
            blogEntity.setImgBackGround(uploadFile.uploadFile(fileBackgroungImg));
        }

        return blogEntity;

    }

    @Transactional
    public void deleteBlog(Integer idBlog) throws Exception {

        BlogEntity blogEntity = blogRepository.findById(idBlog).orElse(null);
        if(blogEntity == null){
            throw new Exception("không  tìm thấy blog cần xóa ");
        }
        blogRepository.delete(blogEntity);

    }

    @Transactional
    public BlogRenderEntity addorUpdateBlogRender(BlogRenderEntity blogRenderEntityParam) throws Exception {
        BlogRenderEntity blogRenderEntitys = blogRenderRepository.findByBlogIdAndType(blogRenderEntityParam.getBlog().getId(),blogRenderEntityParam.getType());
        if(blogRenderEntitys != null){
            throw new Exception("dữ liệu đã tồn tại");
        }
        BlogRenderEntity blogRenderEntity = blogRenderRepository.findByBlogId(blogRenderEntityParam.getBlog().getId());
        if(blogRenderEntity ==null){
            return blogRenderRepository.save(blogRenderEntityParam);
        }else{
           
            if(blogRenderEntityParam.getType()==-1){

                blogRenderRepository.delete(blogRenderEntity);
                return new BlogRenderEntity();
            }

            blogRenderEntity.setBlog(blogRenderEntityParam.getBlog());
            blogRenderEntity.setType(blogRenderEntityParam.getType());
            blogRenderEntity.setTop(blogRenderEntityParam.getTop());

            return blogRenderEntity;

        }



    }


}
