package br.edu.udf.bibliotech.controller;

import br.edu.udf.bibliotech.entities.Emprestimo;
import br.edu.udf.bibliotech.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @PostMapping
    public ResponseEntity<Emprestimo> insert(@RequestBody Emprestimo obj){

        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> findAll() {
        List<Emprestimo> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> findById(@PathVariable Integer id) {
        Emprestimo obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/atrasados")
    public ResponseEntity<List<Emprestimo>> findAtrasados() {
        List<Emprestimo> list = service.findAtrasados();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> update(@PathVariable Integer id, @RequestBody Emprestimo obj) {

        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<Emprestimo> devolverLivro(@PathVariable Integer id) {
        Emprestimo obj = service.devolverLivro(id);
        return ResponseEntity.ok().body(obj);
    }
}
