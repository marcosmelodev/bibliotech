package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Usuario;
import br.edu.udf.bibliotech.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
