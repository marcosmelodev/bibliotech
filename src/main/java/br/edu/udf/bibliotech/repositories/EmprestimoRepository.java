package br.edu.udf.bibliotech.repositories;

import br.edu.udf.bibliotech.entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
}
