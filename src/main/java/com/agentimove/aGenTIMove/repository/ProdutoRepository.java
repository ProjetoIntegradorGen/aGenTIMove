package com.agentimove.aGenTIMove.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentimove.aGenTIMove.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{
	public List<ProdutoModel> findAllByServicoContainingIgnoreCase(String servico);

}
