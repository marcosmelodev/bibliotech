package br.edu.udf.bibliotech.controller;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
