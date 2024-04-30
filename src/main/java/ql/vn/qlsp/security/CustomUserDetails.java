package ql.vn.qlsp.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ql.vn.qlsp.entity.UserEntity;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // thêm quền người dùng cho phiên đăng nhập cần có role_
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+ userEntity.getRole()));
    }

    @Override
    public String getPassword() {
        return userEntity.getPassWord();
    }

    @Override
    public String getUsername() {
        return userEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
