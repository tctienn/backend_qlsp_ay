package ql.vn.qlsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ql.vn.qlsp.entity.CartItemEntity;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity,Integer> {
    CartItemEntity findByIdCartAndProductId(Integer idCard, Integer idProduct);
    List<CartItemEntity> findAllByIdCart(Integer id);

    void deleteAllByIdCart(Integer id);
}
