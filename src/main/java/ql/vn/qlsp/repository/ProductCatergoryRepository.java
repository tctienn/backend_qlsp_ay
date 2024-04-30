package ql.vn.qlsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ql.vn.qlsp.entity.product_catergory.ProductCatergoryEntity;
import ql.vn.qlsp.entity.product_catergory.ProductCatergoryKey;

public interface ProductCatergoryRepository extends JpaRepository<ProductCatergoryEntity, ProductCatergoryKey> {
    Page<ProductCatergoryEntity> findAllByCatergoryId(int id, Pageable pageable);

    Boolean existsByCatergoryId(Integer integer);

}
