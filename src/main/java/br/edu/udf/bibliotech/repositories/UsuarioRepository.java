package br.edu.udf.bibliotech.repositories;

import br.edu.udf.bibliotech.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
