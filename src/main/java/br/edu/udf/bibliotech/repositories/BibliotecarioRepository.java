package br.edu.udf.bibliotech.repositories;

import br.edu.udf.bibliotech.entities.Bibliotecario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecarioRepository extends JpaRepository<Bibliotecario, Integer> {
}
