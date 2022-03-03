package com.agentimove.aGenTIMove.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @NotNull
    private String nome;
    
    @NotNull
    private String email;
    
    @NotNull
    private String senha;
    
    @OneToMany(mappedBy="usuario", cascade=CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List <ProdutoModel> produto;

    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    } 
}
