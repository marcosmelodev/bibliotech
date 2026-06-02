package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Bibliotecario;
import br.edu.udf.bibliotech.repositories.BibliotecarioRepository;
import br.edu.udf.bibliotech.service.exceptions.DatabaseException;
import br.edu.udf.bibliotech.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecarioService {

    @Autowired
    private BibliotecarioRepository repository;

    public List<Bibliotecario> findAll(){
        return repository.findAll();
    }

    public Bibliotecario findById(Integer id){
        Optional<Bibliotecario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Bibliotecario insert(Bibliotecario obj){
        return repository.save(obj);
    }

    public void delete(Integer id){

        if (!repository.existsById(id)){
            throw  new ResourceNotFoundException(id);
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

}
