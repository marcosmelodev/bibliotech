package br.edu.udf.bibliotech.service;

import br.edu.udf.bibliotech.entities.Emprestimo;
import br.edu.udf.bibliotech.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;

    public List<Emprestimo> findAll(){
        return repository.findAll();
    }

    public Emprestimo findById(Integer id){
        Optional<Emprestimo> obj = repository.findById(id);
        return obj.get();
    }
}
