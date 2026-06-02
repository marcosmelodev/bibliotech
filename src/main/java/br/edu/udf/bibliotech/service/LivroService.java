package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Livro;
import br.edu.udf.bibliotech.entities.Livro;
import br.edu.udf.bibliotech.repositories.LivroRepository;
import br.edu.udf.bibliotech.service.exceptions.DatabaseException;
import br.edu.udf.bibliotech.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<Livro> findAll(){
        return repository.findAll();
    }

    public Livro findById(Integer id){
        Optional<Livro> obj = repository.findById(id);
        return obj.get();
    }

    public List<Livro> findByAutor(String autor){
        return repository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Livro> findByTituloContainingIgnoreCase(String titutlo){
        return repository.findByTituloContainingIgnoreCase(titutlo);


    }

    public Livro update(Integer id, Livro obj) {
        try {
            Livro entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Livro entity, Livro obj) {
        entity.setAutor(obj.getAutor());
        entity.setTitulo(obj.getTitulo());
        entity.setAno(obj.getAno());
        entity.setEditora(obj.getEditora());
    }


    public void delete(Integer id) {

        if (!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }

    }

    public Livro insert(Livro obj) {
        obj.setId(null);

        if(obj.getQuantidadeDisponivel() < 0){
            throw new IllegalArgumentException("QUantidade não pode ser negativa");
        }
        return repository.save(obj);
    }
}
