package com.agentimove.aGenTIMove.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

//@Entity
//@Table(name = "tb_compras")
public class ComprasModel {
	
//	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	
	private @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDate obj = LocalDate.now();
	
}
