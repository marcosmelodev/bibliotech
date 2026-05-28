package br.edu.udf.bibliotech.controller;

import br.edu.udf.bibliotech.entities.Professor;
import br.edu.udf.bibliotech.service.AlunoService;
import br.edu.udf.bibliotech.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){
        List<Professor> listaProfessores = service.findAll();
        return ResponseEntity.ok().body(listaProfessores);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id){
        Professor obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
