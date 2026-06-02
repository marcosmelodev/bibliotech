package br.edu.udf.bibliotech.controller;

import br.edu.udf.bibliotech.entities.Aluno;
import br.edu.udf.bibliotech.entities.Professor;
import br.edu.udf.bibliotech.service.AlunoService;
import br.edu.udf.bibliotech.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Professor> insert(@RequestBody Professor obj){

        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri).body(obj);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Professor> update(@PathVariable Integer id, @RequestBody Professor obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();//http = 204
    }

}
