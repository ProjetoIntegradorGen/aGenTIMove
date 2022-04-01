package com.agentimove.aGenTIMove.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
@Table(name = "tb_usuario")

public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotNull(message = "O atributo nome é obrigatório.")
    @Size(min = 2,max = 100)
    private String nome;
    
    @Schema(example = "email@email.com.br")
    @NotNull(message = "O atributo usuário é OBRIGATÓRIO.")
    @Email(message = "O atributo usuário deve ser um email válido.")
    private String usuario;
    
    @NotBlank(message = "O atributo senha é OBRIGATÓRIO.")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caractéres.")
    private String foto;

    private String tipo;

    @OneToMany(mappedBy="usuario", cascade=CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List <ComprasModel> compras;

    public UsuarioModel(Long id, String nome, String usuario, String senha, String foto, String tipo) {
        this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.foto = foto;
		this.tipo = tipo;
    }

    public UsuarioModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<ComprasModel> getCompras() {
        return compras;
    }

    public void setCompras(List<ComprasModel> compras) {
        this.compras = compras;
    }
}
