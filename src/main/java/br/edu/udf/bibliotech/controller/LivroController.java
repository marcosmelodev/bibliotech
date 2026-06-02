package br.edu.udf.bibliotech.controller;

import br.edu.udf.bibliotech.entities.Livro;
import br.edu.udf.bibliotech.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public ResponseEntity<List<Livro>> findAll(){
        List<Livro> listarLivros = service.findAll();
        return ResponseEntity.ok().body(listarLivros);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){
        Livro obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/autor/{autor}")
    public ResponseEntity<List<Livro>> findByName(@PathVariable String autor){
        List<Livro> livros = service.findByAutor(autor);
        return ResponseEntity.ok().body(livros);
    }

    @GetMapping(value = "/livros/{titulo}")
    public ResponseEntity<List<Livro>> findByTitulo(@PathVariable String titulo){
        List<Livro> titulos = service.findByTituloContainingIgnoreCase(titulo);
        return ResponseEntity.ok().body(titulos);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();//http = 204
    }


}
