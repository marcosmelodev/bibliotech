package br.edu.udf.bibliotech.repositories;

import br.edu.udf.bibliotech.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
