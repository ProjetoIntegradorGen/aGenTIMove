package com.agentimove.aGenTIMove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentimove.aGenTIMove.model.ComprasModel;
import com.agentimove.aGenTIMove.repository.ComprasRepository;

@RestController
@RequestMapping("/compras")
@CrossOrigin("*")
public class ComprasController {
	@Autowired
	public 	ComprasRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<ComprasModel>getById(@PathVariable long id){
	return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
	.orElse(ResponseEntity.notFound().build());
	}
	// @GetMapping("/produtos/{produto}")
	// public ResponseEntity<ComprasModel>getByProduto(@PathVariable String produto){
	// 	return repository.findAllByProdutoContainingIgnoreCase(produto).map(resp -> ResponseEntity.ok(resp))
	// 	.orElse(ResponseEntity.notFound().build());
	// }
}
