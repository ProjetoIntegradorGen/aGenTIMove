package com.agentimove.aGenTIMove.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentimove.aGenTIMove.model.ProdutoModel;
import com.agentimove.aGenTIMove.util.Categoria;
import com.agentimove.aGenTIMove.util.Equipamentos;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{
	public List<ProdutoModel> findAllByServicoContainingIgnoreCase(String servico);

	public List<ProdutoModel> findAllByCategoria(Categoria categoria);
	
	public List<ProdutoModel> findAllByCategoriaAndEquipamentos(Categoria categoria, Equipamentos equipamentos);
}
