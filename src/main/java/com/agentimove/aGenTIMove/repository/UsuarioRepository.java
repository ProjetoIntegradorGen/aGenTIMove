package com.agentimove.aGenTIMove.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agentimove.aGenTIMove.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	public Optional<UsuarioModel> findByNome(String nome);
	public Optional<UsuarioModel> findByEmail(String email);
}
