package ql.vn.qlsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ql.vn.qlsp.entity.ImagesEntity;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity,Integer> {
    ImagesEntity findByIdProductAndUrl(Integer id,String url);
}
