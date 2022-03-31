package com.agentimove.aGenTIMove.controller;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

import com.agentimove.aGenTIMove.model.ComprasModel;
import com.agentimove.aGenTIMove.repository.ComprasRepository;
import com.agentimove.aGenTIMove.repository.ProdutoRepository;
import com.agentimove.aGenTIMove.repository.UsuarioRepository;

@RestController
@RequestMapping("/compras")
@Tag(name = "Recursos de Compras", description = "Administração das Compras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComprasController {

	public @Autowired ComprasRepository repository;
	public @Autowired UsuarioRepository repositoryU;
	public @Autowired ProdutoRepository repositoryP;

	@Operation(summary = "Busca compra por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna compra existente"),
			@ApiResponse(responseCode = "400", description = "Compra inexistente"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ComprasModel> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Cria nova Compra")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Compra feita com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro na requisição"),
			@ApiResponse(responseCode = "422", description = "Compra já existente"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PostMapping
	public ResponseEntity<ComprasModel> novaCompra(@RequestBody ComprasModel compra) {
		if (repositoryU.existsById(compra.getUsuario().getId())
				&& repositoryP.existsById(compra.getProduto().getId())) {
			return ResponseEntity.ok(repository.save(compra));
		}
		return ResponseEntity.badRequest().build();
	}

	@Operation(summary = "Atualiza Compra existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Retorna Compra Atualizada"),
			@ApiResponse(responseCode = "400", description = "Erro na requisição"),
			@ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PutMapping
	public ResponseEntity<ComprasModel> atualizaCompra(@Valid @RequestBody ComprasModel compra) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(compra));
	}

	@Operation(summary = "Deleta Compra existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Compra deletada"),
			@ApiResponse(responseCode = "400", description = "Id de postagem inválido"),
	})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
