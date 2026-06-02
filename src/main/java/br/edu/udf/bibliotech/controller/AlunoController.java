package br.edu.udf.bibliotech.controller;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Usuario;
import br.edu.udf.bibliotech.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(){
        List<Aluno> listaAlunos = service.findAll();
        return ResponseEntity.ok().body(listaAlunos);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id){
        Aluno obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody Aluno obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
