package br.edu.udf.bibliotech.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_professor")
public class Professor extends Usuario implements Serializable {

    private String disciplina;

    public Professor(){}

    public Professor(Integer id, String nome, String matricula, String cpf, String email, String disciplina) {
        super(id, nome, matricula, cpf, email);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public int limiteLivros() {
        return 5;
    }


}
