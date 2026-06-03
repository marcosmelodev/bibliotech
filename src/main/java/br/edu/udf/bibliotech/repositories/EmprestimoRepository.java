package br.edu.udf.bibliotech.repositories;

import br.edu.udf.bibliotech.entities.Emprestimo;
import br.edu.udf.bibliotech.entities.Usuario;
import br.edu.udf.bibliotech.entities.enums.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    List<Emprestimo> findByStatus(StatusEmprestimo status);

    List<Emprestimo> findByUsuarioAndStatus(Usuario usuario, StatusEmprestimo statusEmprestimo);

    Optional<Emprestimo> findById(Long id);

    Emprestimo getReferenceById(Long id);
}
