package br.edu.udf.bibliotech.entities;

import br.edu.udf.bibliotech.entities.enums.PerfilUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_bibliotecario")
public class Bibliotecario extends Usuario{

    public Bibliotecario(){}

    public Bibliotecario(Integer id, String nome, String matricula, String cpf, String email, PerfilUsuario perfil) {
        super(id, nome, matricula, cpf, email, perfil);
    }

}
