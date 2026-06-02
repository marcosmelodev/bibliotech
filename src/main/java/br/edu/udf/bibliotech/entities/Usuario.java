package br.edu.udf.bibliotech.entities;

import br.edu.udf.bibliotech.entities.enums.PerfilUsuario;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String matricula;

    private String cpf;
    private String email;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;

    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(){}

    public Usuario(Integer id, String nome, String matricula, String cpf, String email, PerfilUsuario perfil) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.perfil = perfil;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public abstract Integer limiteLivros();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
