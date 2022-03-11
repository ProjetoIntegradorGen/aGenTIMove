package com.agentimove.aGenTIMove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentimove.aGenTIMove.model.ComprasModel;
import com.agentimove.aGenTIMove.repository.ComprasRepository;
import com.agentimove.aGenTIMove.repository.ProdutoRepository;
import com.agentimove.aGenTIMove.repository.UsuarioRepository;

@RestController
@RequestMapping("/compras")
@CrossOrigin("*")
public class ComprasController {

	public @Autowired ComprasRepository repository;
	public @Autowired UsuarioRepository repositoryU;
	public @Autowired ProdutoRepository repositoryP;

	@GetMapping("/{id}")
	public ResponseEntity<ComprasModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ComprasModel> novaCompra(@RequestBody ComprasModel compra) {
		if (repositoryU.existsById(compra.getUsuario().getId())
				&& repositoryP.existsById(compra.getProduto().getId())) {
			return ResponseEntity.ok(repository.save(compra));
		}
		return ResponseEntity.badRequest().build();
	}

}
