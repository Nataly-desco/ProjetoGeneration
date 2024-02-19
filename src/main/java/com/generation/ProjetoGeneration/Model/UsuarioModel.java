package com.generation.ProjetoGeneration.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, message = "O nome tem que ter no minimo 3 caracteres")
    @NotNull(message = "O atributo nome é obrigatorio")
    private String nome;
    @NotNull(message = "O atributo email é obrigatorio ")
    private String email;
    private String foto;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<PostagemModel> postagem;

    public UsuarioModel(Long id, String nome, String email, String foto){
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.email = email;
    }
    public UsuarioModel() {}
    public List<PostagemModel> getPostagem() {
        return postagem;
    }

    public void setPostagem(List<PostagemModel> postagem) {
        this.postagem = postagem;
    }

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
