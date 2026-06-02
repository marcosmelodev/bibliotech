package br.edu.udf.bibliotech.repositories;

import br.edu.udf.bibliotech.entities.Emprestimo;
import br.edu.udf.bibliotech.entities.enums.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    List<Emprestimo> findByStatus(StatusEmprestimo status);
}
