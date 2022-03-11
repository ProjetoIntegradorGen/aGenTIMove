package com.agentimove.aGenTIMove.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agentimove.aGenTIMove.model.ComprasModel;

public interface ComprasRepository extends JpaRepository<ComprasModel, Long> {
	
	public Optional<ComprasModel> findById (Long id);
	
	public Optional<ComprasModel> findByDate (LocalDate data);
	
	public Optional<ComprasModel> findAllByUsuarioContainingIgnoreCase (String usuario);
}
