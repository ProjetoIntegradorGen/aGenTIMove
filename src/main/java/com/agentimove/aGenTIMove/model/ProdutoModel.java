package com.agentimove.aGenTIMove.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.agentimove.aGenTIMove.util.Categoria;
import com.agentimove.aGenTIMove.util.Equipamentos;

@Entity
@Table(name = "tb_produto")
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String servico;

	@NotNull
	private String descricao;

	@NotNull
	private float valor;

	private @Enumerated(EnumType.STRING) Categoria categoria;

	private @Enumerated(EnumType.STRING) Equipamentos equipamentos;

	private String equipDescricao;

	private String urlImagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Equipamentos getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Equipamentos equipamentos) {
		this.equipamentos = equipamentos;
	}

	public String getEquipDescricao() {
		return equipDescricao;
	}

	public void setEquipDescricao(String equipDescricao) {
		this.equipDescricao = equipDescricao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

}