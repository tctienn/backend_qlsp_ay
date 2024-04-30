package ql.vn.qlsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ql.vn.qlsp.entity.BrandsEntity;

@Repository
public interface BrandsRepository extends JpaRepository<BrandsEntity, Integer> {
    boolean existsByName(String name);
}
