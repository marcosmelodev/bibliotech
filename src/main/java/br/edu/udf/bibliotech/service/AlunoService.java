package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Usuario;
import br.edu.udf.bibliotech.repositories.AlunoRepository;
import br.edu.udf.bibliotech.service.exceptions.DatabaseException;
import br.edu.udf.bibliotech.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> findAll(){
        return repository.findAll();
    }

    public Aluno findById(Integer id){
        Optional<Aluno> obj = repository.findById(id);
        return obj.get();
    }

    public Aluno update(Integer id, Aluno obj) {

        try {
            Aluno entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Aluno entity, Aluno obj) {
        entity.setNome(obj.getNome());
        entity.setEmail(obj.getEmail());
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
