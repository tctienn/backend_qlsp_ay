package ql.vn.qlsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ql.vn.qlsp.entity.CatergoryEntity;

@Repository
public interface CatergoryRepository extends JpaRepository<CatergoryEntity,Integer> {
    CatergoryEntity findByName(String name);
}
