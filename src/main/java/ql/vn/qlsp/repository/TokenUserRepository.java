package ql.vn.qlsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ql.vn.qlsp.entity.TokenUserEntity;

public interface TokenUserRepository extends JpaRepository<TokenUserEntity,Integer> {
    TokenUserEntity findByToken(String token);
}
