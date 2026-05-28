package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> findAll(){
        return repository.findAll();
    }
}
