package org.kacang.security.domain;

import java.util.Collection;
import java.util.List;

import org.kacang.domain.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {
    private static final long serialVersionUID = 1L;

    private UserDTO user;

    public CustomUser(UserDTO user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }
}
