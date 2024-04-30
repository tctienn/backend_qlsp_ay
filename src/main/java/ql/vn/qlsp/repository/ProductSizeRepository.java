package ql.vn.qlsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.entity.product_size.ProductSizeEntity;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Integer> {
    Page<ProductSizeEntity> findAllBySizeId(int id, Pageable pageable);

    boolean existsBySizeId(Integer idSize);
}
