package br.edu.udf.bibliotech.entities;

import br.edu.udf.bibliotech.entities.enums.PerfilUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_professor")
public class Professor extends Usuario implements Serializable {

    private String disciplina;

    public Professor(){}

    public Professor(Integer id, String nome, String matricula, String cpf, String email, PerfilUsuario perfil, String disciplina) {
        super(id, nome, matricula, cpf, email, perfil);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public Integer limiteLivros() {
        return 5;
    }


}
