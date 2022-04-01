package com.agentimove.aGenTIMove.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.agentimove.aGenTIMove.Service.UsuarioService;
import com.agentimove.aGenTIMove.model.UserLoginModel;
import com.agentimove.aGenTIMove.model.UsuarioModel;
import com.agentimove.aGenTIMove.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Recursos do Usuario", description = "Administração de uso do usuário no sistema")
public class UsuarioController {

	@Autowired
	public UsuarioRepository repository;
	@Autowired 
	public UsuarioService usuarioService;

	@Operation(summary = "Busca lista de usuarios no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna todos Usuarios"),
			@ApiResponse(responseCode = "400", description = "Retorno sem Usuarios"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioModel>> getAll() {
		List<UsuarioModel> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, " Usuario não encontrado");
		} else {
			return ResponseEntity.ok(repository.findAll());
		}
	}

	@Operation(summary = "Busca usuario por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna Usuario existente"),
			@ApiResponse(responseCode = "400", description = "Usuario inexistente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Busca usuario por nome")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna Lista"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioModel>> getByNome (@PathVariable("nome") String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@Operation(summary = "Faz login do Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario logado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Email ou senha inválidos"),
            @ApiResponse(responseCode = "422", description = "Usuario já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PostMapping ("/logar")
	public ResponseEntity<UserLoginModel> Autentication (@RequestBody Optional<UserLoginModel> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@Operation(summary = "Faz cadastro do Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuario criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "422", description = "Usuario já cadastrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> Post (@RequestBody UsuarioModel usuario){
		return usuarioService.CadastrarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@Operation(summary = "Salvar Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuario salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro ao salvar"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PostMapping
	public ResponseEntity<UsuarioModel> post(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));

	}
	@Operation(summary = "Atualiza Usuario existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Retorna Usuario Atualizado"),
			@ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PutMapping
	public ResponseEntity<UsuarioModel> put(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}

	@Operation(summary = "Deleta Usuario existente por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Retorna Usuario Deletado"),
			@ApiResponse(responseCode = "400", description = "Id usuario inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	@Operation(summary = "Busca perfil por usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Retorna perfil"),
			@ApiResponse(responseCode = "400", description = "Perfil invalido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping("usuario/{usuario}")
	public ResponseEntity<UsuarioModel> getProfile(@PathVariable String usuario){
		return repository.findByUsuario(usuario).map(resp -> {
			return ResponseEntity.ok(resp);
		}).orElse(ResponseEntity.notFound().build());
	}

}
