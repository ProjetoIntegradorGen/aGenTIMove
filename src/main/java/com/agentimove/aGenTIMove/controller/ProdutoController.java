package com.agentimove.aGenTIMove.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.agentimove.aGenTIMove.model.ProdutoModel;
import com.agentimove.aGenTIMove.repository.ProdutoRepository;
import com.agentimove.aGenTIMove.util.Categoria;
import com.agentimove.aGenTIMove.util.Equipamentos;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Recursos de Produtos", description = "Administração do uso de produtos no sistema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	public ProdutoRepository repository;
	
	@Operation(summary = "Busca lista de produtos no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna Produtos"),
			@ApiResponse(responseCode = "400", description = "Retorno sem Produtos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping("/todos")
	public ResponseEntity<List<ProdutoModel>> getAll(){
		List<ProdutoModel> list = repository.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();	
		} else {
			return ResponseEntity.ok(repository.findAll());
		}	
	}
	
	@Operation(summary = "Busca produto por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna produto existente"),
			@ApiResponse(responseCode = "400", description = "Produto inexistente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping ("/{id}")
	public ResponseEntity<ProdutoModel> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@Operation(summary = "Cadastra produto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto cadastrado"),
			@ApiResponse(responseCode = "400", description = "Erro no cadastro do produto"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PostMapping
	public ResponseEntity<ProdutoModel> post(@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(produto));
	}
	
	@Operation(summary = "Atualiza Produto existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Retorna Produto Atualizado"),
			@ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@PutMapping
	public ResponseEntity<ProdutoModel> put(@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(produto));
	}
	
	@Operation(summary = "Deleta Produto existente por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Retorna Produto Deletado"),
			@ApiResponse(responseCode = "400", description = "Id de produto inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

	@Operation(summary = "Busca lista de categorias")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna Categorias"),
			@ApiResponse(responseCode = "400", description = "Retorno sem categoria"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping ("/categorias")
	public ResponseEntity<List<ProdutoModel>> getByCategoria (@RequestParam (defaultValue = "YOGA") Categoria categoria) {
		return ResponseEntity.ok(repository.findAllByCategoria(categoria));
	}
	
	@Operation(summary = "Busca lista de categorias e equipamentos no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna categoria e equipamentos"),
			@ApiResponse(responseCode = "400", description = "Retorno sem categoria e equipamentos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	@GetMapping ("/categoriaequipamentos")
	public ResponseEntity<List<ProdutoModel>> getByCategoriaAndEquipamentos(
			@RequestParam (defaultValue = "YOGA") Categoria categoria,
			@RequestParam (defaultValue = "SIM") Equipamentos equipamentos) {
		return ResponseEntity.ok(repository.findAllByCategoriaAndEquipamentos(categoria, equipamentos));
	}
	
}
