package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Professor;
import br.edu.udf.bibliotech.entities.Professor;
import br.edu.udf.bibliotech.repositories.AlunoRepository;
import br.edu.udf.bibliotech.repositories.ProfessorRepository;
import br.edu.udf.bibliotech.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public List<Professor> findAll(){
        return repository.findAll();
    }

    public Professor findById(Integer id){
        Optional<Professor> obj = repository.findById(id);
        return obj.get();
    }

    public Professor update(Integer id, Professor obj) {
        try {
            Professor entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Professor entity, Professor obj) {
        entity.setNome(obj.getNome());
        entity.setEmail(obj.getEmail());
    }
}
