package ql.vn.qlsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ql.vn.qlsp.entity.SizeEntity;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity , Integer> {

    boolean existsByName(String name);
}
