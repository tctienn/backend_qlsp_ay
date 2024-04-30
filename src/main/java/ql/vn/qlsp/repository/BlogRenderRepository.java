package ql.vn.qlsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ql.vn.qlsp.entity.BlogEntity;
import ql.vn.qlsp.entity.BlogRenderEntity;

import java.util.List;

public interface BlogRenderRepository extends JpaRepository<BlogRenderEntity,Integer> {
    Page<BlogRenderEntity> findAllByType(int type, Pageable pageable);

    BlogRenderEntity findByBlogIdAndType(Integer idBlog, int type);

    BlogRenderEntity findByBlogId(Integer idBlog);
}
