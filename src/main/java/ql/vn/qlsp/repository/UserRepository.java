package ql.vn.qlsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ql.vn.qlsp.entity.ProductEntity;
import ql.vn.qlsp.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String name);
    Optional<UserEntity> findById(Long id);

    @Query(value = "SELECT COUNT(*) FROM user WHERE role = 'admin';", nativeQuery = true)
    Integer coutAdmin();
    @Query(value = "SELECT COUNT(*) FROM user WHERE role = 'user';", nativeQuery = true)
    Integer coutUser();
    @Query(value = "SELECT COUNT(*) FROM user\n" +
            "WHERE COALESCE(role, 'default_role') NOT IN ('user', 'admin');", nativeQuery = true)
    Integer coutUserWait();

    UserEntity findByUserNameAndGmail(String userName , String gamil);
}
