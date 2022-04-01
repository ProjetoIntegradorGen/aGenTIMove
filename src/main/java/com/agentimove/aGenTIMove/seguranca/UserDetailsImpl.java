package com.agentimove.aGenTIMove.seguranca;

import java.util.Collection;
import java.util.List;

import com.agentimove.aGenTIMove.model.UsuarioModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private static final Long serialVersionUID = 1L;

    private String userName; 

    private String password;

    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(UsuarioModel user) {
        this.userName = user.getUsuario();
        this.password = user.getSenha();
    }

    public UserDetailsImpl(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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