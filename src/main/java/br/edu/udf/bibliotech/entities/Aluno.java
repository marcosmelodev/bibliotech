package br.edu.udf.bibliotech.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_aluno")
public class Aluno extends Usuario implements Serializable {

    private String curso;


    public Aluno(){}

   public Aluno(Integer id, String nome, String matricula, String cpf, String email, String curso) {
        super(id, nome, matricula, cpf, email);
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public Integer limiteLivros() {
        return 3;
    }


}
