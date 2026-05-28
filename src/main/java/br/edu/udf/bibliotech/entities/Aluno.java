package br.edu.udf.bibliotech.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_aluno")
public class Aluno extends Usuario {

    private String curso;

    public Aluno(){}

   public Aluno(Integer id, String nome, String matricula, String cpf, String email, String curso) {
        super(id, nome, matricula, cpf, email);
        this.curso = curso;
    }

    @Override
    public int limiteLivros() {
        return 3;
    }
}
