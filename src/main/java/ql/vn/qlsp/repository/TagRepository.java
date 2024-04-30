package ql.vn.qlsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ql.vn.qlsp.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Integer> {
    TagEntity findByName(String name);
}
