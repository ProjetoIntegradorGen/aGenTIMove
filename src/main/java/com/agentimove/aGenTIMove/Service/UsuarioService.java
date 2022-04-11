package com.agentimove.aGenTIMove.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.agentimove.aGenTIMove.model.UserLoginModel;
import com.agentimove.aGenTIMove.model.UsuarioModel;
import com.agentimove.aGenTIMove.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<UsuarioModel> CadastrarUsuario(UsuarioModel usuario) {
		Optional<UsuarioModel> optional = repository.findByEmail(usuario.getEmail());
		if (optional.isPresent()) {
			return Optional.empty();
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.ofNullable(repository.save(usuario));
	}
	
	public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario) {

		if (repository.findById(usuario.getId()).isPresent()) {

			Optional<UsuarioModel> buscaUsuario = repository.findByEmail(usuario.getUsuario());

			if (buscaUsuario.isPresent()) {
				if (buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Usuário já existe!", null);
			}

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(repository.save(usuario));
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
	}


	public Optional<UserLoginModel> Logar(Optional<UserLoginModel> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional <UsuarioModel> usuario = repository.findByEmail(user.get().getEmail());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encoderAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setEmail(usuario.get().getEmail());
				user.get().setSenha(usuario.get().getSenha());
				
				return user;
			}
		}
		return null;
	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

}
