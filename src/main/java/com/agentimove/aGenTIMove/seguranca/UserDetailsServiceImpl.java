package com.agentimove.aGenTIMove.seguranca;

import java.util.Optional;

import com.agentimove.aGenTIMove.model.UsuarioModel;
import com.agentimove.aGenTIMove.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UsuarioRepository userRepository;

        @Override
        public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
            Optional<UsuarioModel> user = userRepository.findByEmail(userName);
            user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));

            return user.map(UserDetailsImpl:: new).get();
        }
}
