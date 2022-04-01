package com.agentimove.aGenTIMove.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.agentimove.aGenTIMove.model.UserLoginModel;
import com.agentimove.aGenTIMove.model.UsuarioModel;
import com.agentimove.aGenTIMove.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<UsuarioModel> CadastrarUsuario(UsuarioModel usuario) {
		Optional<UsuarioModel> optional = repository.findByUsuario(usuario.getUsuario());
		if (optional.isPresent()) {
			return Optional.empty();
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.ofNullable(repository.save(usuario));
	}
	
	public Optional<UserLoginModel> Logar(Optional<UserLoginModel> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional <UsuarioModel> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encoderAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setUsuario(usuario.get().getUsuario());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setTipo(usuario.get().getTipo());
				user.get().setFoto(usuario.get().getFoto());
				
				return user;
			}
		}
		return null;
	}
	
}
