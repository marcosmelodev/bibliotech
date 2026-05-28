package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Usuario;
import br.edu.udf.bibliotech.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario findById(Integer id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.get();
    }
}
