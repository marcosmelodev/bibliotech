package br.edu.udf.bibliotech.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String matricula;

    private String cpf;
    private String email;

    public Usuario(){}

    public Usuario(Integer id, String nome, String matricula, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
    }

    public abstract int limiteLivros();
}
