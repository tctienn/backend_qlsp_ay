package ql.vn.qlsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ql.vn.qlsp.entity.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<ProductEntity,Integer> {

    public Page<ProductEntity> findAllByBrand_Id(int id, Pageable pageable);
//    public Page<ProductEntity> findAllByBrand(@Param("id") int id, Pageable pageable);
    public Page<ProductEntity> findAllByTag_Id(int id, Pageable pageable);
    ProductEntity findById(int id);
    Page<ProductEntity> findAllByNameContaining(String name,Pageable pageable);


    @Query(value = "SELECT p.* \n" +
            "FROM product p \n" +
            "JOIN product_size ps ON p.id = ps.id_product \n" +
            "WHERE ps.id_size = :sizeId", nativeQuery = true)
    List<ProductEntity> findProductsBySizeId(@Param("sizeId") int sizeId);

    @Query(value = "SELECT count(*),p.* \n" +
            "FROM product p \n" +
            "JOIN product_catergory ps ON p.id = ps.id_product \n" +
            "WHERE ps.id_catergory = :catergoryId", nativeQuery = true)
    Page<ProductEntity> findProductsByCatergoryId(@Param("catergoryId") int catergoryId,Pageable pageable);

//    @Query("SELECT p FROM ProductEntity p JOIN p.productSize ps WHERE ps.idSize = ?")
//    List<ProductEntity> findProductsBySizeId(Long sizeId);

    boolean existsByTagId(Integer idTag);
    boolean existsByBrandId(Integer idBrand);

}
