package com.agentimove.aGenTIMove.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agentimove.aGenTIMove.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	public Optional<UsuarioModel> findByNome(String nome);

	//public List<UsuarioModel> findByNome(String nome);

}
