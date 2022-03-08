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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.agentimove.aGenTIMove.Service.UsuarioService;
import com.agentimove.aGenTIMove.model.UserLoginModel;
import com.agentimove.aGenTIMove.model.UsuarioModel;
import com.agentimove.aGenTIMove.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	public UsuarioRepository repository;
	@Autowired 
	public UsuarioService usuarioService;

	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioModel>> getAll() {
		List<UsuarioModel> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Usuario não encontrado");
		} else {
			return ResponseEntity.ok(repository.findAll());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<UsuarioModel> getByNome (@PathVariable("nome") String nome){
		return repository.findByNome(nome).map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro");
					});
	}
	
	@PostMapping ("/logar")
	public ResponseEntity<UserLoginModel> Autentication (@RequestBody Optional<UserLoginModel> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> Post (@RequestBody UsuarioModel usuario){
		return usuarioService.CadastrarUsuario(usuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping
	public ResponseEntity<UsuarioModel> post(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));

	}

	@PutMapping
	public ResponseEntity<UsuarioModel> put(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
